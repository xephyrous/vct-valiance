import {
    BedrockRuntimeClient,
    InvokeModelCommand,
} from "@aws-sdk/client-bedrock-runtime";

import {
    BedrockAgentRuntimeClient,
    RetrieveAndGenerateCommand,
} from "@aws-sdk/client-bedrock-agent-runtime";

import { credCallAWS } from "./firebase-api";

async function InvokeModel(prompt
) {
    return await credCallAWS(_InvokeModel, prompt)
}

async function InvokeRAG(prompt
) {
    return await credCallAWS(_InvokeRAG, prompt)
}

async function _InvokeRAG(prompt, inject) {
    const client = new BedrockAgentRuntimeClient({
        region: "us-east-1",
        credentials: inject
    });

    // Prepare the command for Retrieve and Generate
    const command = new RetrieveAndGenerateCommand({
        input: {
            text: prompt
        },
        retrieveAndGenerateConfiguration: {
            type: "KNOWLEDGE_BASE",
            knowledgeBaseConfiguration: {
                knowledgeBaseId: "DTAK9AFPQ3",
                modelArn: "arn:aws:bedrock:us-east-1::foundation-model/anthropic.claude-instant-v1",
                retrievalConfiguration: {
                    vectorSearchConfiguration: {
                        numberOfResults: 10,
                        overrideSearchType: "HYBRID"
                    },
                },
                generationConfiguration: {
                    promptTemplate: {
                        textPromptTemplate: "You are a helpful assistant that provides information on Valorant's Pro " +
                            "scene and Valorant as a game. Here is information relating to the user's query that may aid" +
                            " you in your response: $search_results$ Please respond according to these rules, however do" +
                            " NOT reference any of the rules directly or mention them in your response\n\nHuman:" +
                            "If a user requests that you generate a team, you will respond with the specified JSON template" +
                            ", filled out to your generated team's specific values, do not add any extraneous elements or" +
                            " anything to the json structure. For roles that you may place in the roles places, you may " +
                            "choose from the following ONLY: duelist, controller, sentinel, and initiator. Place the " +
                            "JSON object at the very END of your response, and place a concise explanation of the team's " +
                            "composition and reasoning behind the players and their roles in the team, followed by a " +
                            "newline, the string '~|TEAM_JSON|~', another newline, then the json." +
                            " Here is the exact JSON structure to follow:{\n" +
                            "  \"name\": \"TEAM_NAME\",\n" +
                            "  \"igl\": \"IGL_MEMBER_NAME\",\n" +
                            "  \"coach\": \"COACH_NAME\",\n" +
                            "  \"members\": [\n" +
                            "    \"MEMBER_1_USERNAME\",\n" +
                            "    \"MEMBER_2_USERNAME\",\n" +
                            "    \"MEMBER_3_USERNAME\",\n" +
                            "    \"MEMBER_4_USERNAME\",\n" +
                            "    \"MEMBER_5_USERNAME\"\n" +
                            "  ],\n" +
                            "  \"roles\": [\n" +
                            "    \"MEMBER_1_ROLE\",\n" +
                            "    \"MEMBER_2_ROLE\",\n" +
                            "    \"MEMBER_3_ROLE\",\n" +
                            "    \"MEMBER_4_ROLE\",\n" +
                            "    \"MEMBER_5_ROLE\"\n" +
                            "  ],\n" +
                            "  \"agents\": [\n" +
                            "    \"MEMBER_1_AGENT\",\n" +
                            "    \"MEMBER_2_AGENT\",\n" +
                            "    \"MEMBER_3_AGENT\",\n" +
                            "    \"MEMBER_4_AGENT\",\n" +
                            "    \"MEMBER_5_AGENT\"\n" +
                            "  ]\n" +
                            "}\n\nAssistant:",
                    },
                    inferenceConfig: {
                        textInferenceConfig: {
                            temperature: 0.7,
                            topP: 0.9,
                            maxTokens: 1000,
                            stopSequences: [],
                        },
                    },
                },
            },
        },
    });

    try {
        const response = await client.send(command);
        return response.output.text;
    } catch(e) { console.log(e.toString()); return null; }
}

async function _InvokeModel(prompt, inject) {
    const client = new BedrockRuntimeClient({
        region: "us-east-1",
        credentials: inject
    });

    const modelId = "amazon.titan-text-premier-v1:0";

    const payload = {
        inputText: prompt,
        textGenerationConfig: {
            maxTokenCount: 3072,
            stopSequences: [],
            temperature: 0,
            topP: 1
        }
    };

    const command = new InvokeModelCommand({
        contentType: "application/json",
        body: JSON.stringify(payload),
        modelId
    });

    try {
        const response = await client.send(command);
        const decodedResponseBody = new TextDecoder().decode(response.body);
        const responseBody = JSON.parse(decodedResponseBody);

        return responseBody.results[0].outputText.toString();
    } catch(_) { return null; }
}

export {
    InvokeModel,
    InvokeRAG
};
