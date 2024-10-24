import {
  HttpRequest as __HttpRequest,
  HttpResponse as __HttpResponse,
} from "@smithy/protocol-http";
import {
  EventStreamSerdeContext as __EventStreamSerdeContext,
  SerdeContext as __SerdeContext,
} from "@smithy/types";
import {
  DeleteAgentMemoryCommandInput,
  DeleteAgentMemoryCommandOutput,
} from "../commands/DeleteAgentMemoryCommand";
import {
  GetAgentMemoryCommandInput,
  GetAgentMemoryCommandOutput,
} from "../commands/GetAgentMemoryCommand";
import {
  InvokeAgentCommandInput,
  InvokeAgentCommandOutput,
} from "../commands/InvokeAgentCommand";
import {
  InvokeFlowCommandInput,
  InvokeFlowCommandOutput,
} from "../commands/InvokeFlowCommand";
import {
  RetrieveAndGenerateCommandInput,
  RetrieveAndGenerateCommandOutput,
} from "../commands/RetrieveAndGenerateCommand";
import {
  RetrieveCommandInput,
  RetrieveCommandOutput,
} from "../commands/RetrieveCommand";
export declare const se_DeleteAgentMemoryCommand: (
  input: DeleteAgentMemoryCommandInput,
  context: __SerdeContext
) => Promise<__HttpRequest>;
export declare const se_GetAgentMemoryCommand: (
  input: GetAgentMemoryCommandInput,
  context: __SerdeContext
) => Promise<__HttpRequest>;
export declare const se_InvokeAgentCommand: (
  input: InvokeAgentCommandInput,
  context: __SerdeContext
) => Promise<__HttpRequest>;
export declare const se_InvokeFlowCommand: (
  input: InvokeFlowCommandInput,
  context: __SerdeContext
) => Promise<__HttpRequest>;
export declare const se_RetrieveCommand: (
  input: RetrieveCommandInput,
  context: __SerdeContext
) => Promise<__HttpRequest>;
export declare const se_RetrieveAndGenerateCommand: (
  input: RetrieveAndGenerateCommandInput,
  context: __SerdeContext
) => Promise<__HttpRequest>;
export declare const de_DeleteAgentMemoryCommand: (
  output: __HttpResponse,
  context: __SerdeContext
) => Promise<DeleteAgentMemoryCommandOutput>;
export declare const de_GetAgentMemoryCommand: (
  output: __HttpResponse,
  context: __SerdeContext
) => Promise<GetAgentMemoryCommandOutput>;
export declare const de_InvokeAgentCommand: (
  output: __HttpResponse,
  context: __SerdeContext & __EventStreamSerdeContext
) => Promise<InvokeAgentCommandOutput>;
export declare const de_InvokeFlowCommand: (
  output: __HttpResponse,
  context: __SerdeContext & __EventStreamSerdeContext
) => Promise<InvokeFlowCommandOutput>;
export declare const de_RetrieveCommand: (
  output: __HttpResponse,
  context: __SerdeContext
) => Promise<RetrieveCommandOutput>;
export declare const de_RetrieveAndGenerateCommand: (
  output: __HttpResponse,
  context: __SerdeContext
) => Promise<RetrieveAndGenerateCommandOutput>;
