package com.github.onefour.blockbooking;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes1;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.1.1.
 */
public final class Purchase_sol_PriceChart extends Contract {
    private static final String BINARY = "60606040526000600355341561001457600080fd5b6040516020806104e08339810160405280805160008054600160a060020a033316600160a060020a03199091161790556004555050610488806100586000396000f3006060604052600436106100825763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630c4f65bd811461008757806367e0badb146100c35780636b8ff574146100e85780638da5cb5b146101335780639d5db22414610146578063b49f4afd14610183578063e757223014610196575b600080fd5b341561009257600080fd5b61009a6101ac565b60405173ffffffffffffffffffffffffffffffffffffffff909116815260200160405180910390f35b34156100ce57600080fd5b6100d66101c9565b60405190815260200160405180910390f35b34156100f357600080fd5b6100fe6004356101cf565b6040517fff00000000000000000000000000000000000000000000000000000000000000909116815260200160405180910390f35b341561013e57600080fd5b61009a61023c565b341561015157600080fd5b6101817fff0000000000000000000000000000000000000000000000000000000000000060043516602435610258565b005b341561018e57600080fd5b6100d661032c565b34156101a157600080fd5b6100d6600435610332565b60005473ffffffffffffffffffffffffffffffffffffffff165b90565b60035490565b600060018281546001816001161561010002031660029004811015156101f157fe5b8154600116156102105790600052602060002090602091828204019190065b90547f0100000000000000000000000000000000000000000000000000000000000000911a0292915050565b60005473ffffffffffffffffffffffffffffffffffffffff1681565b6000543373ffffffffffffffffffffffffffffffffffffffff90811691161461028057600080fd5b600180546002600019610100838516150201909116048082016102a38382610356565b91908154600116156102c45790600052602060002090602091828204019190065b81547f01000000000000000000000000000000000000000000000000000000000000008604601f929092036101000a91820260ff9092021916179055506002805460018101610313838261041e565b5060009182526020909120015550600380546001019055565b60045490565b600060028281548110151561034357fe5b9060005260206000209001549050919050565b81546001816001161561010002031660029004825481601f106103b15782601f10610396575b826008026101000360020a80910402828001178355610419565b60ff1916836000526020600020558180016001018355610419565b82601f106103e1575082600052602060002080549082601f01602090048101906103db919061043e565b5061037c565b5081800160010183558181151161041957601f016020900481601f01602090048360005260206000209182019101610419919061043e565b505050565b815481835581811511610419576000838152602090206104199181019083015b6101c691905b808211156104585760008155600101610444565b50905600a165627a7a72305820cc51b492f374e3f7949b2a6e82c3b5b274d63678828787b8946e07ed4dc4be350029";

    private Purchase_sol_PriceChart(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Purchase_sol_PriceChart(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> getOwnerAddress() {
        Function function = new Function("getOwnerAddress", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getNum() {
        Function function = new Function("getNum", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> getName(BigInteger index) {
        Function function = new Function("getName", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes1>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> addUtil(byte[] name, BigInteger price) {
        Function function = new Function(
                "addUtil", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes1(name), 
                new org.web3j.abi.datatypes.generated.Uint256(price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getBasePrice() {
        Function function = new Function("getBasePrice", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getPrice(BigInteger index) {
        Function function = new Function("getPrice", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<Purchase_sol_PriceChart> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _basePrice) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_basePrice)));
        return deployRemoteCall(Purchase_sol_PriceChart.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Purchase_sol_PriceChart> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _basePrice) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_basePrice)));
        return deployRemoteCall(Purchase_sol_PriceChart.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static Purchase_sol_PriceChart load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Purchase_sol_PriceChart(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Purchase_sol_PriceChart load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Purchase_sol_PriceChart(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
