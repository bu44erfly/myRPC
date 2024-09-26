package com.sam.myRPC.client;


import com.sam.myRPC.common.RPCRequest;
import com.sam.myRPC.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
