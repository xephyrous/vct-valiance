import { AwsSdkSigV4AuthInputConfig, AwsSdkSigV4AuthResolvedConfig, AwsSdkSigV4PreviouslyResolved } from "@aws-sdk/core";
import { HandlerExecutionContext, HttpAuthScheme, HttpAuthSchemeParameters, HttpAuthSchemeParametersProvider, HttpAuthSchemeProvider } from "@smithy/types";
import { BedrockAgentRuntimeClientResolvedConfig } from "../BedrockAgentRuntimeClient";
/**
 * @internal
 */
export interface BedrockAgentRuntimeHttpAuthSchemeParameters extends HttpAuthSchemeParameters {
    region?: string;
}
/**
 * @internal
 */
export interface BedrockAgentRuntimeHttpAuthSchemeParametersProvider extends HttpAuthSchemeParametersProvider<BedrockAgentRuntimeClientResolvedConfig, HandlerExecutionContext, BedrockAgentRuntimeHttpAuthSchemeParameters, object> {
}
/**
 * @internal
 */
export declare const defaultBedrockAgentRuntimeHttpAuthSchemeParametersProvider: (config: BedrockAgentRuntimeClientResolvedConfig, context: HandlerExecutionContext, input: object) => Promise<BedrockAgentRuntimeHttpAuthSchemeParameters>;
/**
 * @internal
 */
export interface BedrockAgentRuntimeHttpAuthSchemeProvider extends HttpAuthSchemeProvider<BedrockAgentRuntimeHttpAuthSchemeParameters> {
}
/**
 * @internal
 */
export declare const defaultBedrockAgentRuntimeHttpAuthSchemeProvider: BedrockAgentRuntimeHttpAuthSchemeProvider;
/**
 * @internal
 */
export interface HttpAuthSchemeInputConfig extends AwsSdkSigV4AuthInputConfig {
    /**
     * Configuration of HttpAuthSchemes for a client which provides default identity providers and signers per auth scheme.
     * @internal
     */
    httpAuthSchemes?: HttpAuthScheme[];
    /**
     * Configuration of an HttpAuthSchemeProvider for a client which resolves which HttpAuthScheme to use.
     * @internal
     */
    httpAuthSchemeProvider?: BedrockAgentRuntimeHttpAuthSchemeProvider;
}
/**
 * @internal
 */
export interface HttpAuthSchemeResolvedConfig extends AwsSdkSigV4AuthResolvedConfig {
    /**
     * Configuration of HttpAuthSchemes for a client which provides default identity providers and signers per auth scheme.
     * @internal
     */
    readonly httpAuthSchemes: HttpAuthScheme[];
    /**
     * Configuration of an HttpAuthSchemeProvider for a client which resolves which HttpAuthScheme to use.
     * @internal
     */
    readonly httpAuthSchemeProvider: BedrockAgentRuntimeHttpAuthSchemeProvider;
}
/**
 * @internal
 */
export declare const resolveHttpAuthSchemeConfig: <T>(config: T & HttpAuthSchemeInputConfig & AwsSdkSigV4PreviouslyResolved) => T & HttpAuthSchemeResolvedConfig;
