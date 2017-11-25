package com.github.onefour.blockbooking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes1;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.1.1.
 */
public final class Purchase_sol_Purchase extends Contract {
    private static final String BINARY = "60606040526040516020806107248339810160405280805160048054600160a060020a03808416600160a060020a0319928316179283905560038054338316931692909217909155919350169050630c4f65bd6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15156100a257600080fd5b6102c65a03f115156100b357600080fd5b505050604051805160028054600160a060020a031916600160a060020a039283161790553460008181556014820460015560045491935091169063b49f4afd90604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b151561014157600080fd5b6102c65a03f1151561015257600080fd5b5050506040518051905014151561016857600080fd5b506105ac806101786000396000f30060606040526004361061007f5763ffffffff60e060020a60003504166308551a53811461008457806335a063b4146100b357806344f06701146100c85780637150d8ae146100d3578063826d3dec146100e6578063a035b1fe1461010b578063c19d93fb1461011e578063d696069714610155578063ebe629d714610168575b600080fd5b341561008f57600080fd5b610097610170565b604051600160a060020a03909116815260200160405180910390f35b34156100be57600080fd5b6100c661017f565b005b6100c6600435610267565b34156100de57600080fd5b6100976103ef565b34156100f157600080fd5b6100f96103fe565b60405190815260200160405180910390f35b341561011657600080fd5b6100f9610404565b341561012957600080fd5b61013161040a565b6040518082600281111561014157fe5b60ff16815260200191505060405180910390f35b341561016057600080fd5b6100c661041a565b6100c66104ed565b600254600160a060020a031681565b60035433600160a060020a0390811691161461019a57600080fd5b60008060045460a060020a900460ff1660028111156101b557fe5b146101bf57600080fd5b7f72c874aeff0b183a56e2b79c71b46e1aed4dee5e09862134b8821ba2fddbf8bf60405160405180910390a16004805474ff0000000000000000000000000000000000000000191674020000000000000000000000000000000000000000179055600254600154600160a060020a039091169080156108fc0290604051600060405180830381858888f19350505050151561025957600080fd5b600354600160a060020a0316ff5b60018060045460a060020a900460ff16600281111561028257fe5b1461028c57600080fd5b60035433600160a060020a039081169116146102a757600080fd5b6004543490600160a060020a031663e75722308460006040516020015260405160e060020a63ffffffff84160281526004810191909152602401602060405180830381600087803b15156102fa57600080fd5b6102c65a03f1151561030b57600080fd5b5050506040518051905014151561032157600080fd5b6004547f163e09c54602eb031b27f6da8c68420690bf93dff32966c090f37b416c77f16b90600160a060020a0316636b8ff5748460006040516020015260405160e060020a63ffffffff84160281526004810191909152602401602060405180830381600087803b151561039457600080fd5b6102c65a03f115156103a557600080fd5b505050604051805190506040517fff00000000000000000000000000000000000000000000000000000000000000909116815260200160405180910390a150506000805434019055565b600354600160a060020a031681565b60015481565b60005481565b60045460a060020a900460ff1681565b60035433600160a060020a0390811691161461043557600080fd5b60018060045460a060020a900460ff16600281111561045057fe5b1461045a57600080fd5b7fd5d55c8a68912e9a110618df8d5e2e83b8d83211c57a8ddd1203df92885dc88160405160405180910390a1600480546002919074ff0000000000000000000000000000000000000000191660a060020a830217905550600254600160a060020a039081169030163180156108fc0290604051600060405180830381858888f1935050505015156104ea57600080fd5b50565b60035433600160a060020a0390811691161461050857600080fd5b60008060045460a060020a900460ff16600281111561052357fe5b1461052d57600080fd5b7f7dc978f66f3c519e05c40ed47ba67ee7bf11257bdd46f6c4fe47c051951c538760405160405180910390a1506004805474ff0000000000000000000000000000000000000000191660a060020a1790555600a165627a7a72305820b8fa7ad4f6e6302df3e4f320524bce71e7cfc36dee472971849396dc244d10520029";

    private Purchase_sol_Purchase(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Purchase_sol_Purchase(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<AbortedEventResponse> getAbortedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Aborted", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<AbortedEventResponse> responses = new ArrayList<AbortedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            AbortedEventResponse typedResponse = new AbortedEventResponse();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AbortedEventResponse> abortedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Aborted", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, AbortedEventResponse>() {
            @Override
            public AbortedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                AbortedEventResponse typedResponse = new AbortedEventResponse();
                return typedResponse;
            }
        });
    }

    public List<BuyMoreEventResponse> getBuyMoreEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("BuyMore", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes1>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<BuyMoreEventResponse> responses = new ArrayList<BuyMoreEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            BuyMoreEventResponse typedResponse = new BuyMoreEventResponse();
            typedResponse.name = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BuyMoreEventResponse> buyMoreEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("BuyMore", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes1>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, BuyMoreEventResponse>() {
            @Override
            public BuyMoreEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                BuyMoreEventResponse typedResponse = new BuyMoreEventResponse();
                typedResponse.name = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public List<PurchaseConfirmedEventResponse> getPurchaseConfirmedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("PurchaseConfirmed", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<PurchaseConfirmedEventResponse> responses = new ArrayList<PurchaseConfirmedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            PurchaseConfirmedEventResponse typedResponse = new PurchaseConfirmedEventResponse();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<PurchaseConfirmedEventResponse> purchaseConfirmedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("PurchaseConfirmed", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, PurchaseConfirmedEventResponse>() {
            @Override
            public PurchaseConfirmedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                PurchaseConfirmedEventResponse typedResponse = new PurchaseConfirmedEventResponse();
                return typedResponse;
            }
        });
    }

    public List<RoomReceivedEventResponse> getRoomReceivedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("RoomReceived", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<RoomReceivedEventResponse> responses = new ArrayList<RoomReceivedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            RoomReceivedEventResponse typedResponse = new RoomReceivedEventResponse();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RoomReceivedEventResponse> roomReceivedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("RoomReceived", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, RoomReceivedEventResponse>() {
            @Override
            public RoomReceivedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                RoomReceivedEventResponse typedResponse = new RoomReceivedEventResponse();
                return typedResponse;
            }
        });
    }

    public RemoteCall<String> seller() {
        Function function = new Function("seller", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> abort() {
        Function function = new Function(
                "abort", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> buyMore(BigInteger index, BigInteger weiValue) {
        Function function = new Function(
                "buyMore", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<String> buyer() {
        Function function = new Function("buyer", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> punish() {
        Function function = new Function("punish", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> price() {
        Function function = new Function("price", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> state() {
        Function function = new Function("state", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> confirmPurchase() {
        Function function = new Function(
                "confirmPurchase", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> confirmArriving(BigInteger weiValue) {
        Function function = new Function(
                "confirmArriving", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public static RemoteCall<Purchase_sol_Purchase> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _priceChart) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_priceChart)));
        return deployRemoteCall(Purchase_sol_Purchase.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<Purchase_sol_Purchase> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _priceChart) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_priceChart)));
        return deployRemoteCall(Purchase_sol_Purchase.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Purchase_sol_Purchase load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Purchase_sol_Purchase(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Purchase_sol_Purchase load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Purchase_sol_Purchase(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class AbortedEventResponse {
    }

    public static class BuyMoreEventResponse {
        public byte[] name;
    }

    public static class PurchaseConfirmedEventResponse {
    }

    public static class RoomReceivedEventResponse {
    }
}
