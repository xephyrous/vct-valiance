import {
    BedrockRuntimeClient,
    InvokeModelCommand,
} from "@aws-sdk/client-bedrock-runtime";

import {
    BedrockAgentRuntimeClient,
    RetrieveAndGenerateCommand,
} from "@aws-sdk/client-bedrock-agent-runtime";

import {
    credCallAWS,
    getMessages
} from "./firebase-api";

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

    const messages = await getMessages();
    let history = "BEGIN HISTORY";

    // Build conversation history
    for (let i = 0; i < messages["systemMessages"].length; i++) {
        if (messages["systemMessages"][i].length !== 0) {
            history += "System: " + messages["systemMessages"][i] + "\n";
        }
        if (messages["userMessages"][i].length !== 0 && i !== messages.length) {
            history += "User: " + messages["userMessages"][i] + "\n";
        }
    }

    history += "END HISTORY";

    console.log(history);

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
                        textPromptTemplate: "You are a helpful assistant focused on providing information about Valorant's " +
                            "Pro scene and game-related questions. Here is relevant information from external sources: $search_results$. " +
                            "This is the history of your conversation of the user so far:" + history +
                            "Respond to user inquiries based on the information above.\n\n" +
                            "Do NOT generate a team unless the user explicitly requests one. Otherwise, simply answer their query.\n\n" +
                            "If a user requests team generation, use this exact format for your response. First, provide a concise explanation " +
                            "about the team's composition and reasoning, then follow the JSON structure below noting the following rules, " +
                            "only use the following roles (controller, sentinel, initiator, duelist), the members' names " +
                            "must be their in-game username, the coach's name in the 'coach' field MUST be one of the members on the team," +
                            "every member must be a vct player or professional valorant player's username (do not place the agent's name instead)," +
                            "you cannot ever have more than ONE of the same agent on a team UNIQUE AGENTS ONLY," +
                            "If there are multiple of the same agent then CHANGE IT," +
                            "the team data must come AFTER your response, at the very end, each role must be the CORRECT" +
                            " role for their agent. Here is the JSON structure:\n" +
                            "~||TEAMDATA||~\n" +
                            "{\n" +
                            "  \"name\": \"TEAM_NAME\",\n" +
                            "  \"igl\": \"IGL_MEMBER_NAME\",\n" +
                            "  \"coach\": \"COACH_NAME\",\n" +
                            "  \"uuid\": \"\",\n" +
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
                            "}\n\n" +
                            "Human: " + prompt + "\nAssistant:",
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
