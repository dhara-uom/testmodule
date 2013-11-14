package org.dhara.portal.test.northtest;

/**
 * Created with IntelliJ IDEA.
 * User: newair
 * Date: 11/13/13
 * Time: 9:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestData {

    public static String className= "DynamicDeploy";
    public static String classContent=WPS52NorthConfig.defaultPackage +
            "\n" +
            "\n" +
            "import java.net.URISyntaxException;\n" +
            "import java.util.*;\n" +
            "\n" +
            "\n" +
            "\n" +
            "import org.n52.wps.io.data.IData;\n" +
            "import org.n52.wps.io.data.binding.literal.LiteralIntBinding;\n" +
            "import org.n52.wps.io.data.binding.literal.LiteralStringBinding;\n" +
            "import org.n52.wps.server.AbstractSelfDescribingAlgorithm;\n" +
            "\n" +
            "import java.io.BufferedReader;\n" +
            "import java.io.DataOutputStream;\n" +
            "import java.io.IOException;\n" +
            "import java.io.InputStreamReader;\n" +
            "import java.net.HttpURLConnection;\n" +
            "import java.net.MalformedURLException;\n" +
            "import java.net.URL;\n" +
            "\n" +
            "import javax.net.ssl.HttpsURLConnection;\n" +
            "\n" +
            "/*\n" +
            " * This is a sample 52North WPS Process Class.\n" +
            " * It inherits from AbstractSelfDescribingAlgorithm to automcatically create the ProcessDescription document.  */\n" +
            "public class DynamicDeploy extends AbstractSelfDescribingAlgorithm {\n" +
            "\n" +
            "      /*\n" +
            "     * Method to retrieve the identifiers for the input data elements.\n" +
            "     * The ProcessDescription is automatically created with this data.\n" +
            "     *\n" +
            "     * @return List of input identifiers\n" +
            "     */\n" +
            "\n" +
            "    private static Map<String, Object> outputs;\n" +
            "    private final String USER_AGENT = \"Mozilla/5.0\";\n" +
            "    private String url = \"http://localhost:8093/J2EETEST/MyServlet\";\n" +
            "\n" +
            "    @Override\n" +
            "    public List<String> getInputIdentifiers() {\n" +
            "        List<String> identifiers = new ArrayList<String>();\n" +
            "        identifiers.add(\"Layer1\");\n" +
            "        return identifiers;\n" +
            "    }\n" +
            "\n" +
            "    /*\n" +
            "     * Method to retrieve the identifiers for the output data elements.\n" +
            "     * The ProcessDescription is automatically created with this data.\n" +
            "     *\n" +
            "     * @return List of output identifiers\n" +
            "     */\n" +
            "    @Override\n" +
            "    public List<String> getOutputIdentifiers() {\n" +
            "        List<String> identifiers = new ArrayList<String>();\n" +
            "        identifiers.add(\"result\");\n" +
            "        return identifiers;\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    /*\n" +
            "     * This method returns the Class of the accepted Inputdata format.\n" +
            "     * It is used to determine a suitable parser and automatically announce accepted datatypes for input data in the DescribeProcess response.\n" +
            "     *\n" +
            "     * @param identifier Identifier of the Inputdata element (see ProcessDescription)\n" +
            "     * @return Class of the accepted Inputdata format based on the given identifier.\n" +
            "     */\n" +
            "    @Override\n" +
            "    public Class getInputDataType(String identifier) {\n" +
            "        if (identifier.equals(\"Layer1\")) {\n" +
            "            return LiteralIntBinding.class;\n" +
            "        }\n" +
            "        throw new RuntimeException(\"Error: Wrong identifier\");\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    /*\n" +
            "     * This method returns the Class of the accepted Outputdata format.\n" +
            "     * It is used to determine a suitable generator and automatically announce provided datatypes for output data in the DescribeProcess response.\n" +
            "     *\n" +
            "     * @param identifier Identifier of the Outputdata element (see ProcessDescription)\n" +
            "     * @return Class of the accepted Oututdata format based on the given identifier.\n" +
            "     */\n" +
            "    @Override\n" +
            "    public Class getOutputDataType(String identifier) {\n" +
            "        if (identifier.equals(\"result\")) {\n" +
            "            return LiteralIntBinding.class;\n" +
            "        }\n" +
            "        throw new RuntimeException(\"Error: Wrong identifier\");\n" +
            "    }\n" +
            "\n" +
            "    /*\n" +
            "     * Main method to perform the processing.\n" +
            "     * All business logic shall go in this method.\n" +
            "     *\n" +
            "     *\n" +
            "     * @param inputMap Parsed inputdata identified by the identifiers provided in the getInputIdentifiers()  method and automatically announced in the DescribeProcess response\n" +
            "     * @return Map containing the output data as IData identified by the identifiers given in getOutputIdentifiers().\n" +
            "     */\n" +
            "    @Override\n" +
            "    public Map<String, IData> run(Map<String, List<IData>> inputMap) {\n" +
            "\n" +
            "        List<IData> layer1List = inputMap.get(\"Layer1\");\n" +
            "\n" +
            "        if (layer1List.size() == 0) {\n" +
            "            throw new RuntimeException(\"Invalid Input Parameters\");\n" +
            "        }\n" +
            "\n" +
            "        IData layer1 = layer1List.get(0);\n" +
            "\n" +
            "        Integer inputData = (Integer) layer1.getPayload();\n" +
            "\n" +
            "\n" +
            " /*   Map<String,Object> outputs = null;\n" +
            "  //  int out = inputData +4;\n" +
            "    WorkflowInvocationAPI workflowInvocationAPI= null;\n" +
            "    try {\n" +
            "        workflowInvocationAPI = new WorkflowInvocationAPIImpl();\n" +
            "\n" +
            "        Map<String,Object> inputs=new HashMap<String, Object>();\n" +
            "        inputs.put(\"input\", 11);\n" +
            "        String experimentId=workflowInvocationAPI.executeWorkflow(inputs,\"52NorthTest\");\n" +
            "\n" +
            "        outputs=workflowInvocationAPI.getWorkflowOutputs(experimentId);\n" +
            "\n" +
            "    } catch (InterruptedException e) {\n" +
            "        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.\n" +
            "    } catch (URISyntaxException e) {\n" +
            "        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.\n" +
            "    } catch (Exception e) {\n" +
            "        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "    Set keySet =  outputs.keySet();\n" +
            "\n" +
            "\n" +
            "    IData result = new LiteralIntBinding(Integer.parseInt((String)outputs.get(keySet.toArray()[0])));*/\n" +
            "\n" +
            "\n" +
            "   /* WorkflowInvocationAPI workflowInvocationAPI= null;\n" +
            "    try {\n" +
            "        workflowInvocationAPI = new WorkflowInvocationAPIImpl();\n" +
            "\n" +
            "        Map<String,Object> inputs=new HashMap<String, Object>();\n" +
            "        inputs.put(\"input\",11);\n" +
            "        String experimentId=workflowInvocationAPI.executeWorkflow(inputs,\"52NorthTest\");      //     _52NorthExecuteWorkFlowTest\n" +
            "        outputs=workflowInvocationAPI.getWorkflowOutputs(experimentId);\n" +
            "    } catch (URISyntaxException e) {\n" +
            "        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.\n" +
            "    } catch (Exception e) {\n" +
            "        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.\n" +
            "    }\n" +
            "*/\n" +
            "        String out = null;\n" +
            "\n" +
            "        try {\n" +
            "            out = sendGet(inputData);\n" +
            "        } catch (IOException e) {\n" +
            "            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.\n" +
            "        }\n" +
            "\n" +
            "\n" +
            "        IData result = null;\n" +
            "        result = new LiteralIntBinding(Integer.parseInt(out));\n" +
            "\n" +
            "//new Map created\n" +
            "        Map<String, IData> resultMap = new HashMap<String, IData>();\n" +
            "\n" +
            "//created response added to corresponding identifier (see this.getOutputIdentifiers())\n" +
            "\n" +
            "\n" +
            "        resultMap.put(\"result\", result);\n" +
            "        return resultMap;\n" +
            "    }\n" +
            "\n" +
            "    private String sendGet(Integer inputData) throws IOException {\n" +
            "\n" +
            "        url = url + \"?input=\" + inputData;\n" +
            "        URL obj = new URL(url);\n" +
            "\n" +
            "        HttpURLConnection con = (HttpURLConnection) obj.openConnection();\n" +
            "\n" +
            "        // optional default is GET\n" +
            "        con.setRequestMethod(\"GET\");\n" +
            "\n" +
            "        //add request header\n" +
            "        con.setRequestProperty(\"User-Agent\", USER_AGENT);\n" +
            "\n" +
            "        int responseCode = con.getResponseCode();\n" +
            "        System.out.println(\"\\nSending 'GET' request to URL : \" + url);\n" +
            "        System.out.println(\"Response Code : \" + responseCode);\n" +
            "\n" +
            "        BufferedReader in = new BufferedReader(\n" +
            "                new InputStreamReader(con.getInputStream()));\n" +
            "        String inputLine;\n" +
            "        StringBuffer response = new StringBuffer();\n" +
            "\n" +
            "        while ((inputLine = in.readLine()) != null) {\n" +
            "            response.append(inputLine);\n" +
            "        }\n" +
            "        in.close();\n" +
            "\n" +
            "        //print result\n" +
            "        System.out.println(response.toString());\n" +
            "        return response.toString();\n" +
            "\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "}\n";



}
