import { HttpHandlerOptions as __HttpHandlerOptions } from "@smithy/types";
import { BedrockAgentRuntimeClient } from "./BedrockAgentRuntimeClient";
import {
  DeleteAgentMemoryCommandInput,
  DeleteAgentMemoryCommandOutput,
} from "./commands/DeleteAgentMemoryCommand";
import {
  GetAgentMemoryCommandInput,
  GetAgentMemoryCommandOutput,
} from "./commands/GetAgentMemoryCommand";
import {
  InvokeAgentCommandInput,
  InvokeAgentCommandOutput,
} from "./commands/InvokeAgentCommand";
import {
  InvokeFlowCommandInput,
  InvokeFlowCommandOutput,
} from "./commands/InvokeFlowCommand";
import {
  RetrieveAndGenerateCommandInput,
  RetrieveAndGenerateCommandOutput,
} from "./commands/RetrieveAndGenerateCommand";
import {
  RetrieveCommandInput,
  RetrieveCommandOutput,
} from "./commands/RetrieveCommand";
export interface BedrockAgentRuntime {
  deleteAgentMemory(
    args: DeleteAgentMemoryCommandInput,
    options?: __HttpHandlerOptions
  ): Promise<DeleteAgentMemoryCommandOutput>;
  deleteAgentMemory(
    args: DeleteAgentMemoryCommandInput,
    cb: (err: any, data?: DeleteAgentMemoryCommandOutput) => void
  ): void;
  deleteAgentMemory(
    args: DeleteAgentMemoryCommandInput,
    options: __HttpHandlerOptions,
    cb: (err: any, data?: DeleteAgentMemoryCommandOutput) => void
  ): void;
  getAgentMemory(
    args: GetAgentMemoryCommandInput,
    options?: __HttpHandlerOptions
  ): Promise<GetAgentMemoryCommandOutput>;
  getAgentMemory(
    args: GetAgentMemoryCommandInput,
    cb: (err: any, data?: GetAgentMemoryCommandOutput) => void
  ): void;
  getAgentMemory(
    args: GetAgentMemoryCommandInput,
    options: __HttpHandlerOptions,
    cb: (err: any, data?: GetAgentMemoryCommandOutput) => void
  ): void;
  invokeAgent(
    args: InvokeAgentCommandInput,
    options?: __HttpHandlerOptions
  ): Promise<InvokeAgentCommandOutput>;
  invokeAgent(
    args: InvokeAgentCommandInput,
    cb: (err: any, data?: InvokeAgentCommandOutput) => void
  ): void;
  invokeAgent(
    args: InvokeAgentCommandInput,
    options: __HttpHandlerOptions,
    cb: (err: any, data?: InvokeAgentCommandOutput) => void
  ): void;
  invokeFlow(
    args: InvokeFlowCommandInput,
    options?: __HttpHandlerOptions
  ): Promise<InvokeFlowCommandOutput>;
  invokeFlow(
    args: InvokeFlowCommandInput,
    cb: (err: any, data?: InvokeFlowCommandOutput) => void
  ): void;
  invokeFlow(
    args: InvokeFlowCommandInput,
    options: __HttpHandlerOptions,
    cb: (err: any, data?: InvokeFlowCommandOutput) => void
  ): void;
  retrieve(
    args: RetrieveCommandInput,
    options?: __HttpHandlerOptions
  ): Promise<RetrieveCommandOutput>;
  retrieve(
    args: RetrieveCommandInput,
    cb: (err: any, data?: RetrieveCommandOutput) => void
  ): void;
  retrieve(
    args: RetrieveCommandInput,
    options: __HttpHandlerOptions,
    cb: (err: any, data?: RetrieveCommandOutput) => void
  ): void;
  retrieveAndGenerate(
    args: RetrieveAndGenerateCommandInput,
    options?: __HttpHandlerOptions
  ): Promise<RetrieveAndGenerateCommandOutput>;
  retrieveAndGenerate(
    args: RetrieveAndGenerateCommandInput,
    cb: (err: any, data?: RetrieveAndGenerateCommandOutput) => void
  ): void;
  retrieveAndGenerate(
    args: RetrieveAndGenerateCommandInput,
    options: __HttpHandlerOptions,
    cb: (err: any, data?: RetrieveAndGenerateCommandOutput) => void
  ): void;
}
export declare class BedrockAgentRuntime
  extends BedrockAgentRuntimeClient
  implements BedrockAgentRuntime {}
