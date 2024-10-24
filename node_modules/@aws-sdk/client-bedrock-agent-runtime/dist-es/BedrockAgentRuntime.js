import { createAggregatedClient } from "@smithy/smithy-client";
import { BedrockAgentRuntimeClient } from "./BedrockAgentRuntimeClient";
import { DeleteAgentMemoryCommand, } from "./commands/DeleteAgentMemoryCommand";
import { GetAgentMemoryCommand, } from "./commands/GetAgentMemoryCommand";
import { InvokeAgentCommand } from "./commands/InvokeAgentCommand";
import { InvokeFlowCommand } from "./commands/InvokeFlowCommand";
import { RetrieveAndGenerateCommand, } from "./commands/RetrieveAndGenerateCommand";
import { RetrieveCommand } from "./commands/RetrieveCommand";
const commands = {
    DeleteAgentMemoryCommand,
    GetAgentMemoryCommand,
    InvokeAgentCommand,
    InvokeFlowCommand,
    RetrieveCommand,
    RetrieveAndGenerateCommand,
};
export class BedrockAgentRuntime extends BedrockAgentRuntimeClient {
}
createAggregatedClient(commands, BedrockAgentRuntime);
