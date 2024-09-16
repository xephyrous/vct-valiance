import {
    BedrockRuntimeClient,
    InvokeModelCommand,
} from "@aws-sdk/client-bedrock-runtime";

import { credCallAWS } from "./firebase-api";

async function InvokeModel(prompt) {
    await credCallAWS(_InvokeModel, prompt)
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

    console.log("[ QUERY ]");
    console.log(JSON.stringify(payload));

    const command = new InvokeModelCommand({
        contentType: "application/json",
        body: JSON.stringify(payload),
        modelId
    });

    try {
        const response = await client.send(command);

        const decodedResponseBody = new TextDecoder().decode(response.body);
        const responseBody = JSON.parse(decodedResponseBody);

        console.log(responseBody.results[0].outputText.toString())

        return responseBody.results[0].outputText.toString();
    } catch (err) {
        console.error(`ERROR: Can't invoke ${modelId}. Reason: ${err}`);
    }
}

export {
    InvokeModel
};
