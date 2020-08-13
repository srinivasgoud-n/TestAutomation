package org.test.automation.utils;

import java.util.List;

import com.relevantcodes.extentreports.LogStatus;


public class RestAPIUtility extends APIBasePage {

	public static Response createGETRequest(String endPoint) {
		Response response = null;
		RestAssured.baseURI = endPoint;
		try {
			response = RestAssured.given().header("Accept", "*/*").header("token", access_token).get();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, e);
		}
		return response;
	}

	public static Response createPostRequest(String endPoint, String requestBody) {
		Response response = null;
		RestAssured.baseURI = endPoint;
		try {
			if (requestBody != null) {
				response = RestAssured.given().contentType(ContentType.JSON).header("Accept", "application/json")
						.header("token", access_token).body(requestBody).post();
			} else {
				response = RestAssured.given().contentType(ContentType.JSON).header("Accept", "application/json")
						.header("token", access_token).post();
			}

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, e);
		}
		return response;
	}

	public static Response createPUTRequest(String endPoint, String requestBody) {
		Response response = null;
		RestAssured.baseURI = endPoint;
		try {
			response = RestAssured.given().contentType(ContentType.JSON).header("Accept", "application/json")
					.header("token", access_token).body(requestBody).put();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, e);
		}
		return response;
	}

	public static Response createDeleteRequest(String endPoint) {
		Response response = null;
		RestAssured.baseURI = endPoint;
		try {
			response = RestAssured.given().contentType(ContentType.JSON).header("Accept", "application/json")
					.header("token", access_token).delete();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, e);
		}
		return response;
	}

	public static String createNodeRequestBody(String nodeID, String name) {
		return "{\n" + "    \"id\": \"" + nodeID + "\",\n" + "    \"name\": \"" + name + "\",\n"
				+ "    \"type\": \"Trailer\",\n" + "    \"classType\": \"Asset\"\n" + "}";
	}

	public static String updateNodeRequestBody(String updatedName) {
		return "{\n" + "    \"name\": \"" + updatedName + "\"\n" + "}";
	}

	public static String createRelationshipRequestBody(String firstNodeID, String secondNodeID) {
		return "{\n" + "    \"name\": \"TaggedAsset\",\n" + "    \"type\": \"TaggedAsset\",\n" + "    \"from\":\""
				+ firstNodeID + "\",\n" + "    \"to\":\"" + secondNodeID + "\",\n"
				+ "    \"classType\": \"TaggedAsset\"\n" + "}";
	}

	public static String updateRelationshipRequestBody(String modifiedBy) {
		return "{\n" + "	\n" + "	\"modified_by\":\"" + modifiedBy + "\"\n" + "}";
	}

	public static String parseJson(Response response, String property) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		return jsonPathEvaluator.get(property);
	}

	public static List<Object> getResponseList(Response response, String property) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		return jsonPathEvaluator.getList(property);
	}

	public static String createRuleTemplateRequestBody(String label, String name, String nodeID) {
		return "{\n" + "	\"data\": {\n" + "		\"nodeClass\": \"_RuleTemplate\",\n"
				+ "		\"nodeProperties\": {\n" + "			\"name\": \"" + name + "\",\n"
				+ "			\"label\": \"" + label + "\"\n" + "		}\n" + "	},\n" + "	\"children\": [\n"
				+ "		{\n" + "			\"data\": {\n" + "				\"edgeClass\": \"_True\",\n"
				+ "				\"edgeProperties\": {},\n" + "				\"nodeClass\": \"_Location\",\n"
				+ "				\"nodeProperties\": {\n" + "					\"name\": \"Location:At\",\n"
				+ "					\"sid\": \"Location\",\n" + "					\"cid\": \"At\",\n"
				+ "					\"unit\": \"__unit__\",\n" + "					\"operator\": \"__operator__\",\n"
				+ "					\"value\": \"__value__\",\n" + "					\"label\": \"Location\"\n"
				+ "				}\n" + "			},\n" + "			\"children\": [\n" + "				{\n"
				+ "					\"data\": {\n" + "						\"edgeClass\": \"_True\",\n"
				+ "						\"edgeProperties\": {},\n"
				+ "						\"nodeClass\": \"_DwellTime\",\n"
				+ "						\"nodeProperties\": {\n"
				+ "							\"name\": \"DwellTime:High\",\n"
				+ "							\"sid\": \"DwellTime\",\n"
				+ "							\"cid\": \"High\",\n"
				+ "							\"unit\": \"__unit__\",\n"
				+ "							\"operator\": \"__operator__\",\n"
				+ "							\"value\": \"__value__\",\n"
				+ "							\"label\": \"DwellTime\"\n" + "						}\n"
				+ "					}\n" + "					\n" + "				},\n" + "				{\n"
				+ "					\"children\": [\n" + "						{\n"
				+ "							\"data\": {\n" + "								\"edgeClass\": \"_True\",\n"
				+ "								\"edgeProperties\": {},\n"
				+ "								\"nodeClass\": \"_Temperature\",\n"
				+ "								\"nodeProperties\": {\n"
				+ "									\"name\": \"" + nodeID + ":" + nodeID + "\",\n"
				+ "									\"sid\": \"" + nodeID + "\",\n"
				+ "									\"cid\": \"" + nodeID + "\",\n"
				+ "									\"unit\": \"__unit__\",\n"
				+ "									\"operator\": \"__operator__\",\n"
				+ "									\"value\": \"__value__\",\n"
				+ "									\"label\": \"Temperature\"\n" + "								}\n"
				+ "							},\n" + "							\"children\": []\n"
				+ "						}\n" + "					]\n" + "				}\n" + "			]\n"
				+ "		},\n" + "		{\n" + "			\"data\": {\n"
				+ "				\"edgeClass\": \"_Action\",\n" + "				\"edgeProperties\": {},\n"
				+ "				\"nodeClass\": \"_Webhook\",\n" + "				\"nodeProperties\": {\n"
				+ "					\"endpoint\": \"__http__\",\n" + "					\"message\": \"__message__\",\n"
				+ "					\"label\": \"WebHook\"\n" + "				}\n" + "			}\n"
				+ "			\n" + "		}\n" + "	]\n" + "}";
	}

	public static Response createEntityType(String endpoint, String entityID, String entityName, String cdmID,
			String cdmName) {

		RestAssured.baseURI = endpoint;

		requestBody = "{\n" + "    \"id\": \"" + entityID + "\",\n" + "    \"name\": \"" + entityName + "\",\n"
				+ "    \"classType\": \"EntityClass\",\n" + "    \"graphType\": \"Node\",\n" + "    \"cdmFields\": [\n"
				+ "        {\n" + "            \"id\": \"" + cdmID + "\",\n" + "            \"name\": \"" + cdmName
				+ "\",\n" + "            \"type\": \"String\",\n" + "            \"size\": 255,\n"
				+ "            \"unit\": \"Default\",\n" + "            \"required\": false\n" + "        }\n"
				+ "    ]\n" + "}";

		response = RestAssured.given().contentType(ContentType.JSON).header("Accept", "application/json")
				.header("token", access_token).queryParam("superClass", "Asset").body(requestBody).post();
		return response;

	}

	public static Response createMappingForEntity(String endPoint) {

		requestBody = "{\n" + "	\"id\":\"id\",\n" + "	\"name\":\"name\",\n" + "	\"gtype\":\"graphType\"\n" + "}";

		response = RestAPIUtility.createPostRequest(endPoint, requestBody);

		return response;
	}

	public static Response createBulkMappingForAllEntityTypesForaGivenTenant(String endPoint) {

		requestBody = "[\n" + "    {\n" + "        \"type\": \"Geofence\",\n" + "		\"cdmType\": \"Geofence\",\n"
				+ "        \"mappingFields\": {\n" + "            \"name\": \"area_name\",\n"
				+ "            \"id\": \"id\",\n" + "            \"type\": \"area_type\",\n"
				+ "            \"coordinates\":\"coordinates\",\n" + "            \"countryCode\":\"country_code\",\n"
				+ "            \"time\":\"created_time\",\n" + "            \"properties\":\"properties\",\n"
				+ "			\"tenantId\":\"tenant_id\"\n" + "        }\n" + "    },\n" + "    {\n"
				+ "       \"type\": \"Route\",\n" + "	   \"cdmType\": \"Route\",\n" + "        \"mappingFields\": {\n"
				+ "            \"id1\": \"shipment_id\",\n" + "            \"id2\": \"area_id\",\n"
				+ "			\"routeId\": \"route_id\",\n" + "			\"tenantId\":\"tenant_id\"\n" + "        }\n"
				+ "    },\n" + "    {\n" + "        \"type\": \"Shipment\",\n" + "		\"cdmType\": \"Shipment\",\n"
				+ "        \"mappingFields\": {\n" + "            \"name\": \"shipment_name\",\n"
				+ "            \"id\": \"shipment_id\",\n" + "			\"status\": \"status_code\",\n"
				+ "			\"aat\": \"actual_arrival_time\",\n" + "			\"adt\": \"actual_dep_time\",\n"
				+ "			\"bol\": \"bol\",\n" + "			\"tenantId\":\"tenant_id\"\n" + "        }\n"
				+ "    },\n" + "    {\n" + "        \"type\": \"Asset\",\n" + "		\"cdmType\": \"Asset\",\n"
				+ "        \"mappingFields\": {\n" + "            \"name\": \"name\",\n"
				+ "            \"id\": \"id\",\n" + "			\"type\": \"type\",\n"
				+ "			\"tenantId\":\"tenant_id\"\n" + "        }\n" + "    },\n" + "    {\n"
				+ "        \"type\": \"Sensor\",\n" + "		\"cdmType\": \"Sensor\",\n"
				+ "        \"mappingFields\": {\n" + "            \"name\": \"id\",\n" + "            \"id\": \"id\",\n"
				+ "			\"tenantId\":\"tenant_id\"\n" + "        }\n" + "    },\n" + "    {\n"
				+ "        \"type\": \"Gateway\",\n" + "		\"cdmType\": \"Gateway\",\n"
				+ "        \"mappingFields\": {\n" + "            \"name\": \"physical_id\",\n"
				+ "            \"id\": \"id\",\n" + "			\"tenantId\":\"tenant_id\"\n" + "        }\n"
				+ "    },\n" + "    {\n" + "        \"type\": \"Segment\",\n" + "		\"cdmType\": \"Segment\",\n"
				+ "        \"mappingFields\": {\n" + "			\"id1\": \"from_area\",\n"
				+ "			\"id2\": \"to_area\",\n" + "			\"routeId\":\"id\",\n"
				+ "			\"tenantId\":\"tenant_id\"\n" + "        }\n" + "    },\n" + "	{\n"
				+ "        \"type\": \"Has\",\n" + "		\"cdmType\": \"Has\",\n" + "        \"mappingFields\": {\n"
				+ "            \"id1\": \"shipment_id\",\n" + "            \"id2\": \"asset_id\",\n"
				+ "			\"tenantId\":\"tenant_id\"\n" + "        }\n" + "    }\n" + "]";

		response = RestAPIUtility.createPostRequest(endPoint, requestBody);

		return response;

	}

	public static Response createNode(String endPoint, String nodeID, String name) {

		requestBody = "{\n" + "    \"id\": \"" + nodeID + "\",\n" + "    \"name\": \"" + name + "\",\n"
				+ "    \"type\": \"Trailer\",\n" + "    \"classType\": \"Asset\"\n" + "}";

		response = RestAPIUtility.createPostRequest(endPoint, requestBody);

		return response;
	}

	public static Response createRelationShip(String endPoint, String firstNodeID, String secondNodeID) {

		requestBody = "{\n" + "    \"name\": \"TaggedAsset\",\n" + "    \"type\": \"TaggedAsset\",\n"
				+ "    \"from\":\"" + firstNodeID + "\",\n" + "    \"to\":\"" + secondNodeID + "\",\n"
				+ "    \"classType\": \"TaggedAsset\"\n" + "}";

		response = RestAPIUtility.createPostRequest(endPoint, requestBody);

		return response;
	}

	public static Response createRuleTemplate(String endPoint, String name, String label, String nodeID) {

		requestBody = "{\n" + "    \"data\": {\n" + "        \"nodeClass\": \"_RuleTemplate\",\n"
				+ "        \"nodeProperties\": {\n" + "        	\"name\":\"" + name + "\",\n"
				+ "        	\"label\" : \"" + label + "\"\n" + "        }\n" + "    },\n" + "    \"children\": [\n"
				+ "        {\n" + "            \"data\": {\n" + "                \"edgeClass\": \"_True\",\n"
				+ "                \"edgeProperties\": {},\n" + "				\"nodeClass\": \"_Location\",\n"
				+ "                \"nodeProperties\": {\n" + "					\"name\":\"Location:At\",\n"
				+ "					\"sid\":\"Location\",\n" + "					\"cid\":\"At\",\n"
				+ "					\"unit\":\"__unit__\",\n" + "					\"operator\":\"__operator__\",\n"
				+ "					\"value\":\"__value__\",\n" + "					\"label\" : \"Location\"\n"
				+ "				}\n" + "            },\n" + "            \"children\": [\n" + "				{\n"
				+ "				\"data\": {\n" + "					\"edgeClass\": \"_True\",\n"
				+ "					\"edgeProperties\": {},\n" + "					\"nodeClass\": \"_DwellTime\",\n"
				+ "					\"nodeProperties\": {\n" + "						\"name\":\"DwellTime:High\",\n"
				+ "						\"sid\":\"DwellTime\",\n" + "						\"cid\":\"High\",\n"
				+ "						\"unit\":\"__unit__\",\n"
				+ "						\"operator\":\"__operator__\",\n"
				+ "						\"value\":\"__value__\",\n" + "						\"label\" : \"DwellTime\"\n"
				+ "					}\n" + "				},\n" + "				\"children\": [\n"
				+ "					{\n" + "						\"data\": {\n"
				+ "						\"edgeClass\": \"_True\",\n"
				+ "						\"edgeProperties\": {},\n"
				+ "						\"nodeClass\": \"_Temperature\",\n"
				+ "						\"nodeProperties\": {\n" + "							\"name\":\"" + nodeID
				+ ":" + nodeID + "\",\n" + "							\"sid\":\"" + nodeID + "\",\n"
				+ "							\"cid\":\"" + nodeID + "\",\n"
				+ "							\"unit\":\"__unit__\",\n"
				+ "							\"operator\":\"__operator__\",\n"
				+ "							\"value\":\"__value__\",\n"
				+ "							\"label\" : \"Temperature\"\n" + "						}\n"
				+ "					},\n" + "					\"children\": []\n" + "					}\n"
				+ "				]\n" + "				},\n" + "				{\n" + "				\"data\": {\n"
				+ "					\"edgeClass\": \"_True\",\n" + "					\"edgeProperties\": {},\n"
				+ "					\"nodeClass\": \"_DwellTime\",\n" + "					\"nodeProperties\": {\n"
				+ "						\"name\":\"DwellTime:High\",\n"
				+ "						\"sid\":\"DwellTime\",\n" + "						\"cid\":\"High\",\n"
				+ "						\"unit\":\"__unit__\",\n"
				+ "						\"operator\":\"__operator__\",\n"
				+ "						\"value\":\"__value__\",\n" + "						\"label\" : \"DwellTime\"\n"
				+ "					}\n" + "				},\n" + "				\"children\": [\n"
				+ "					{\n" + "						\"data\": {\n"
				+ "						\"edgeClass\": \"_True\",\n"
				+ "						\"edgeProperties\": {},\n"
				+ "						\"nodeClass\": \"_Temperature\",\n"
				+ "						\"nodeProperties\": {\n" + "							\"name\":\"" + nodeID
				+ ":" + nodeID + "\",\n" + "							\"sid\":\"" + nodeID + "\",\n"
				+ "							\"cid\":\"" + nodeID + "\",\n"
				+ "							\"unit\":\"__unit__\",\n"
				+ "							\"operator\":\"__operator__\",\n"
				+ "							\"value\":\"__value__\",\n"
				+ "							\"label\" : \"Temperature\"\n" + "						}\n"
				+ "					},\n" + "					\"children\": []\n" + "					}\n"
				+ "				]\n" + "				}\n" + "			]\n" + "        },\n" + "		{\n"
				+ "            \"data\": {\n" + "                \"edgeClass\": \"_True\",\n"
				+ "                \"edgeProperties\": {},\n" + "				\"nodeClass\": \"_Location\",\n"
				+ "                \"nodeProperties\": {\n" + "					\"name\":\"Location:At\",\n"
				+ "					\"sid\":\"Location\",\n" + "					\"cid\":\"At\",\n"
				+ "					\"unit\":\"__unit__\",\n" + "					\"operator\":\"__operator__\",\n"
				+ "					\"value\":\"__value__\",\n" + "					\"label\" : \"Location\"\n"
				+ "				}\n" + "            },\n" + "            \"children\": [\n" + "				{\n"
				+ "				\"data\": {\n" + "					\"edgeClass\": \"_True\",\n"
				+ "					\"edgeProperties\": {},\n" + "					\"nodeClass\": \"_DwellTime\",\n"
				+ "					\"nodeProperties\": {\n" + "						\"name\":\"DwellTime:High\",\n"
				+ "						\"sid\":\"DwellTime\",\n" + "						\"cid\":\"High\",\n"
				+ "						\"unit\":\"__unit__\",\n"
				+ "						\"operator\":\"__operator__\",\n"
				+ "						\"value\":\"__value__\",\n" + "						\"label\" : \"DwellTime\"\n"
				+ "					}\n" + "				},\n" + "				\"children\": [\n"
				+ "					{\n" + "						\"data\": {\n"
				+ "						\"edgeClass\": \"_True\",\n"
				+ "						\"edgeProperties\": {},\n"
				+ "						\"nodeClass\": \"_Temperature\",\n"
				+ "						\"nodeProperties\": {\n" + "							\"name\":\"" + nodeID
				+ ":" + nodeID + "\",\n" + "							\"sid\":\"" + nodeID + "\",\n"
				+ "							\"cid\":\"" + nodeID + "\",\n"
				+ "							\"unit\":\"__unit__\",\n"
				+ "							\"operator\":\"__operator__\",\n"
				+ "							\"value\":\"__value__\",\n"
				+ "							\"label\" : \"Temperature\"\n" + "						}\n"
				+ "					},\n" + "					\"children\": [\n" + "						{\n"
				+ "						\"data\": {\n" + "						\"edgeClass\": \"_True\",\n"
				+ "						\"edgeProperties\": {},\n"
				+ "						\"nodeClass\": \"_Humidity\",\n"
				+ "						\"nodeProperties\": {\n"
				+ "							\"name\":\"AA20:AA21\",\n" + "							\"sid\":\"AA20\",\n"
				+ "							\"cid\":\"AA21\",\n"
				+ "							\"unit\":\"__unit__\",\n"
				+ "							\"operator\":\"__operator__\",\n"
				+ "							\"value\":\"__value__\",\n"
				+ "							\"label\" : \"Humidity\"\n" + "						}\n"
				+ "						},\n" + "						\"children\": []\n" + "					}\n"
				+ "					]\n" + "					}\n" + "				]\n" + "				},\n"
				+ "				{\n" + "				\"data\": {\n" + "					\"edgeClass\": \"_True\",\n"
				+ "					\"edgeProperties\": {},\n" + "					\"nodeClass\": \"_DwellTime\",\n"
				+ "					\"nodeProperties\": {\n" + "						\"name\":\"DwellTime:High\",\n"
				+ "						\"sid\":\"DwellTime\",\n" + "						\"cid\":\"High\",\n"
				+ "						\"unit\":\"__unit__\",\n"
				+ "						\"operator\":\"__operator__\",\n"
				+ "						\"value\":\"__value__\",\n" + "						\"label\" : \"DwellTime\"\n"
				+ "					}\n" + "				},\n" + "				\"children\": [\n"
				+ "					{\n" + "						\"data\": {\n"
				+ "						\"edgeClass\": \"_True\",\n"
				+ "						\"edgeProperties\": {},\n"
				+ "						\"nodeClass\": \"_Temperature\",\n"
				+ "						\"nodeProperties\": {\n" + "							\"name\":\"" + nodeID
				+ ":" + nodeID + "\",\n" + "							\"sid\":\"" + nodeID + "\",\n"
				+ "							\"cid\":\"" + nodeID + "\",\n"
				+ "							\"unit\":\"__unit__\",\n"
				+ "							\"operator\":\"__operator__\",\n"
				+ "							\"value\":\"__value__\",\n"
				+ "							\"label\" : \"Temperature\"\n" + "						}\n"
				+ "					},\n" + "					\"children\": []\n" + "					}\n"
				+ "				]\n" + "				}\n" + "			]\n" + "        },\n" + "		{\n"
				+ "            \"data\": {\n" + "                \"edgeClass\": \"_Action\",\n"
				+ "                \"edgeProperties\": {},\n" + "				\"nodeClass\": \"_Webhook\",\n"
				+ "                \"nodeProperties\": {\n" + "					\"endpoint\":\"__http__\",\n"
				+ "					\"message\":\"__message__\",\n" + "					\"label\" : \"WebHook\"\n"
				+ "				}\n" + "            },\n" + "            \"children\": [\n" + "				{\n"
				+ "				\"data\": {\n" + "					\"edgeClass\": \"_Action\",\n"
				+ "					\"edgeProperties\": {},\n" + "					\"nodeClass\": \"_Sms\",\n"
				+ "					\"nodeProperties\": {\n" + "						\"recipent\":\"__http__\",\n"
				+ "						\"message\":\"__message__\",\n" + "						\"label\" : \"SMS\"\n"
				+ "					}\n" + "				},\n" + "				\"children\": []\n"
				+ "				}\n" + "			]\n" + "        }\n" + "	]\n" + "}";

		response = RestAPIUtility.createPostRequest(endPoint, requestBody);

		return response;
	}

	public static Response createComplexRuleActionsToRuleNode(String endPoint) {

		requestBody = "{\n" + "  \"data\": {\n" + "    \"nodeClass\": \"_Class\",\n" + "    \"nodeProperties\": {\n"
				+ "      \"all\": \"false\",\n" + "      \"ids\": [\n" + "        \"Pallets\",\n" + "        \"Bins\"\n"
				+ "      ]\n" + "    }\n" + "  },\n" + "  \"children\": [\n" + "    {\n" + "      \"data\": {\n"
				+ "        \"nodeClass\": \"_Rule\",\n" + "        \"nodeProperties\": {\n"
				+ "          \"name\": \"Fac Freezer Rule\",\n"
				+ "          \"ruleTemplateId\": \"d603aa7e-4325-40e1-90bc-c0c1be9a99e9\"\n" + "        },\n"
				+ "        \"edgeClass\": \"RuleEdge\",\n" + "        \"edgeProperties\": {\n"
				+ "          \"label\": \"Rule\"\n" + "        }\n" + "      },\n" + "      \"children\": [\n"
				+ "        {\n" + "          \"data\": {\n" + "            \"edgeClass\": \"_True\",\n"
				+ "            \"edgeProperties\": {},\n" + "            \"nodeClass\": \"_Location\",\n"
				+ "            \"nodeProperties\": {\n" + "              \"name\": \"Location:At\",\n"
				+ "              \"sid\": \"Location\",\n" + "              \"cid\": \"At\",\n"
				+ "              \"unit\": \"Default\",\n" + "              \"operator\": \"eq\",\n"
				+ "              \"value\": \"Location-Id-001\"\n" + "            }\n" + "          },\n"
				+ "          \"children\": [\n" + "            {\n" + "              \"data\": {\n"
				+ "                \"edgeClass\": \"_True\",\n" + "                \"edgeProperties\": {},\n"
				+ "                \"nodeClass\": \"_DwellTime\",\n" + "                \"nodeProperties\": {\n"
				+ "                  \"name\": \"DwellTime:High\",\n" + "                  \"sid\": \"DwellTime\",\n"
				+ "                  \"cid\": \"High\",\n" + "                  \"unit\": \"Default\",\n"
				+ "                  \"operator\": \"gteq\",\n" + "                  \"value\": \"20\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": [\n"
				+ "                {\n" + "                  \"data\": {\n"
				+ "                    \"edgeClass\": \"_True\",\n" + "                    \"edgeProperties\": {},\n"
				+ "                    \"nodeClass\": \"_Temperature\",\n"
				+ "                    \"nodeProperties\": {\n"
				+ "                      \"name\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4:37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"sid\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"cid\": \"37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"unit\": \"F\",\n" + "                      \"operator\": \"gt\",\n"
				+ "                      \"value\": \"98\"\n" + "                    }\n" + "                  },\n"
				+ "                  \"children\": []\n" + "                }\n" + "              ]\n"
				+ "            },\n" + "            {\n" + "              \"data\": {\n"
				+ "                \"edgeClass\": \"_True\",\n" + "                \"edgeProperties\": {},\n"
				+ "                \"nodeClass\": \"_DwellTime\",\n" + "                \"nodeProperties\": {\n"
				+ "                  \"name\": \"DwellTime:High\",\n" + "                  \"sid\": \"DwellTime\",\n"
				+ "                  \"cid\": \"High\",\n" + "                  \"unit\": \"Default\",\n"
				+ "                  \"operator\": \"gteq\",\n" + "                  \"value\": \"30\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": [\n"
				+ "                {\n" + "                  \"data\": {\n"
				+ "                    \"edgeClass\": \"_True\",\n" + "                    \"edgeProperties\": {},\n"
				+ "                    \"nodeClass\": \"_Temperature\",\n"
				+ "                    \"nodeProperties\": {\n"
				+ "                      \"name\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4:37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"sid\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"cid\": \"37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"unit\": \"F\",\n" + "                      \"operator\": \"gt\",\n"
				+ "                      \"value\": \"95\"\n" + "                    }\n" + "                  },\n"
				+ "                  \"children\": []\n" + "                }\n" + "              ]\n"
				+ "            }\n" + "          ]\n" + "        },\n" + "        {\n" + "          \"data\": {\n"
				+ "            \"edgeClass\": \"_True\",\n" + "            \"edgeProperties\": {},\n"
				+ "            \"nodeClass\": \"_Location\",\n" + "            \"nodeProperties\": {\n"
				+ "              \"name\": \"Location:At\",\n" + "              \"sid\": \"Location\",\n"
				+ "              \"cid\": \"At\",\n" + "              \"unit\": \"Default\",\n"
				+ "              \"operator\": \"eq\",\n" + "              \"value\": \"Location-Id-001\"\n"
				+ "            }\n" + "          },\n" + "          \"children\": [\n" + "            {\n"
				+ "              \"data\": {\n" + "                \"edgeClass\": \"_True\",\n"
				+ "                \"edgeProperties\": {},\n" + "                \"nodeClass\": \"_DwellTime\",\n"
				+ "                \"nodeProperties\": {\n" + "                  \"name\": \"DwellTime:High\",\n"
				+ "                  \"sid\": \"DwellTime\",\n" + "                  \"cid\": \"High\",\n"
				+ "                  \"unit\": \"Default\",\n" + "                  \"operator\": \"gteq\",\n"
				+ "                  \"value\": \"20\"\n" + "                }\n" + "              },\n"
				+ "              \"children\": [\n" + "                {\n" + "                  \"data\": {\n"
				+ "                    \"edgeClass\": \"_True\",\n" + "                    \"edgeProperties\": {},\n"
				+ "                    \"nodeClass\": \"_Temperature\",\n"
				+ "                    \"nodeProperties\": {\n"
				+ "                      \"name\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4:37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"sid\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"cid\": \"37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"unit\": \"F\",\n" + "                      \"operator\": \"gt\",\n"
				+ "                      \"value\": \"92\"\n" + "                    }\n" + "                  },\n"
				+ "                  \"children\": [\n" + "                    {\n"
				+ "                      \"data\": {\n" + "                        \"edgeClass\": \"_True\",\n"
				+ "                        \"edgeProperties\": {},\n"
				+ "                        \"nodeClass\": \"_Humidity\",\n"
				+ "                        \"nodeProperties\": {\n"
				+ "                          \"name\": \"AA20:AA21\",\n"
				+ "                          \"sid\": \"AA20\",\n" + "                          \"cid\": \"AA21\",\n"
				+ "                          \"unit\": \"%\",\n" + "                          \"operator\": \"gt\",\n"
				+ "                          \"value\": \"40\"\n" + "                        }\n"
				+ "                      },\n" + "                      \"children\": []\n" + "                    }\n"
				+ "                  ]\n" + "                }\n" + "              ]\n" + "            },\n"
				+ "            {\n" + "              \"data\": {\n" + "                \"edgeClass\": \"_True\",\n"
				+ "                \"edgeProperties\": {},\n" + "                \"nodeClass\": \"_DwellTime\",\n"
				+ "                \"nodeProperties\": {\n" + "                  \"name\": \"DwellTime:High\",\n"
				+ "                  \"sid\": \"DwellTime\",\n" + "                  \"cid\": \"High\",\n"
				+ "                  \"unit\": \"Default\",\n" + "                  \"operator\": \"gteq\",\n"
				+ "                  \"value\": \"25\"\n" + "                }\n" + "              },\n"
				+ "              \"children\": [\n" + "                {\n" + "                  \"data\": {\n"
				+ "                    \"edgeClass\": \"_True\",\n" + "                    \"edgeProperties\": {},\n"
				+ "                    \"nodeClass\": \"_Temperature\",\n"
				+ "                    \"nodeProperties\": {\n"
				+ "                      \"name\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4:37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"sid\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"cid\": \"37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"unit\": \"F\",\n" + "                      \"operator\": \"gt\",\n"
				+ "                      \"value\": \"90\"\n" + "                    }\n" + "                  },\n"
				+ "                  \"children\": []\n" + "                }\n" + "              ]\n"
				+ "            }\n" + "          ]\n" + "        },\n" + "        {\n" + "          \"data\": {\n"
				+ "            \"edgeClass\": \"_Action\",\n" + "            \"edgeProperties\": {},\n"
				+ "            \"nodeClass\": \"_Webhook\",\n" + "            \"nodeProperties\": {\n"
				+ "              \"endpoint\": \"http://scn.cloudleaf.io/api/webhook\",\n"
				+ "              \"message\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "            }\n" + "          },\n" + "          \"children\": [\n" + "            {\n"
				+ "              \"data\": {\n" + "                \"edgeClass\": \"_Action\",\n"
				+ "                \"edgeProperties\": {},\n" + "                \"nodeClass\": \"_Sms\",\n"
				+ "                \"nodeProperties\": {\n" + "                  \"recipent\": \"+16500123456\",\n"
				+ "                  \"message\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": []\n" + "            }\n"
				+ "          ]\n" + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}";

		response = RestAPIUtility.createPostRequest(endPoint, requestBody);

		return response;
	}

	public static Response createComplexRuleTailedActions(String endPoint) {

		requestBody = "{\n" + "  \"data\": {\n" + "    \"nodeClass\": \"_Class\",\n" + "    \"nodeProperties\": {\n"
				+ "      \"all\": \"false\",\n" + "      \"classIds\": [\n" + "        \"Pallets\",\n"
				+ "        \"Bins\"\n" + "      ]\n" + "    }\n" + "  },\n" + "  \"children\": [\n" + "    {\n"
				+ "      \"data\": {\n" + "        \"nodeClass\": \"_Rule\",\n" + "        \"nodeProperties\": {\n"
				+ "          \"name\": \"Fac Freezer Rule\",\n"
				+ "          \"ruleTemplateId\": \"d603aa7e-4325-40e1-90bc-c0c1be9a99e9\"\n" + "        },\n"
				+ "        \"edgeClass\": \"RuleEdge\",\n" + "        \"edgeProperties\": {\n"
				+ "          \"label\": \"Rule\"\n" + "        }\n" + "      },\n" + "      \"children\": [\n"
				+ "        {\n" + "          \"data\": {\n" + "            \"edgeClass\": \"_True\",\n"
				+ "            \"edgeProperties\": {},\n" + "            \"nodeClass\": \"_Location\",\n"
				+ "            \"nodeProperties\": {\n" + "              \"name\": \"Location:At\",\n"
				+ "              \"sid\": \"Location\",\n" + "              \"cid\": \"At\",\n"
				+ "              \"unit\": \"Default\",\n" + "              \"operator\": \"eq\",\n"
				+ "              \"value\": \"Location-Id-001\"\n" + "            }\n" + "          },\n"
				+ "          \"children\": [\n" + "            {\n" + "              \"data\": {\n"
				+ "                \"edgeClass\": \"_True\",\n" + "                \"edgeProperties\": {},\n"
				+ "                \"nodeClass\": \"_DwellTime\",\n" + "                \"nodeProperties\": {\n"
				+ "                  \"name\": \"DwellTime:High\",\n" + "                  \"sid\": \"DwellTime\",\n"
				+ "                  \"cid\": \"High\",\n" + "                  \"unit\": \"Default\",\n"
				+ "                  \"operator\": \"gteq\",\n" + "                  \"value\": \"20\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": [\n"
				+ "                {\n" + "                  \"data\": {\n"
				+ "                    \"edgeClass\": \"_True\",\n" + "                    \"edgeProperties\": {},\n"
				+ "                    \"nodeClass\": \"_Temperature\",\n"
				+ "                    \"nodeProperties\": {\n"
				+ "                      \"name\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4:37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"sid\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"cid\": \"37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"unit\": \"F\",\n" + "                      \"operator\": \"gt\",\n"
				+ "                      \"value\": \"98\"\n" + "                    }\n" + "                  },\n"
				+ "                  \"children\": [\n" + "                  	{\n" + "          \"data\": {\n"
				+ "            \"edgeClass\": \"_Action\",\n" + "            \"edgeProperties\": {},\n"
				+ "            \"nodeClass\": \"_Webhook\",\n" + "            \"nodeProperties\": {\n"
				+ "              \"endpoint\": \"http://scn.cloudleaf.io/api/webhook\",\n"
				+ "              \"message\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "            }\n" + "          },\n" + "          \"children\": [\n" + "            {\n"
				+ "              \"data\": {\n" + "                \"edgeClass\": \"_Action\",\n"
				+ "                \"edgeProperties\": {},\n" + "                \"nodeClass\": \"_Sms\",\n"
				+ "                \"nodeProperties\": {\n" + "                  \"recipent\": \"+16500123456\",\n"
				+ "                  \"message\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": []\n" + "            }\n"
				+ "          ]\n" + "        }\n" + "                  ]\n" + "                }\n"
				+ "              ]\n" + "            },\n" + "            {\n" + "              \"data\": {\n"
				+ "                \"edgeClass\": \"_True\",\n" + "                \"edgeProperties\": {},\n"
				+ "                \"nodeClass\": \"_DwellTime\",\n" + "                \"nodeProperties\": {\n"
				+ "                  \"name\": \"DwellTime:High\",\n" + "                  \"sid\": \"DwellTime\",\n"
				+ "                  \"cid\": \"High\",\n" + "                  \"unit\": \"Default\",\n"
				+ "                  \"operator\": \"gteq\",\n" + "                  \"value\": \"30\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": [\n"
				+ "                {\n" + "                  \"data\": {\n"
				+ "                    \"edgeClass\": \"_True\",\n" + "                    \"edgeProperties\": {},\n"
				+ "                    \"nodeClass\": \"_Temperature\",\n"
				+ "                    \"nodeProperties\": {\n"
				+ "                      \"name\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4:37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"sid\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"cid\": \"37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"unit\": \"F\",\n" + "                      \"operator\": \"gt\",\n"
				+ "                      \"value\": \"95\"\n" + "                    }\n" + "                  },\n"
				+ "                  \"children\": [\n" + "                  	{\n" + "          \"data\": {\n"
				+ "            \"edgeClass\": \"_Action\",\n" + "            \"edgeProperties\": {},\n"
				+ "            \"nodeClass\": \"_Webhook\",\n" + "            \"nodeProperties\": {\n"
				+ "              \"endpoint\": \"http://scn.cloudleaf.io/api/webhook\",\n"
				+ "              \"message\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "            }\n" + "          },\n" + "          \"children\": [\n" + "            {\n"
				+ "              \"data\": {\n" + "                \"edgeClass\": \"_Action\",\n"
				+ "                \"edgeProperties\": {},\n" + "                \"nodeClass\": \"_Sms\",\n"
				+ "                \"nodeProperties\": {\n" + "                  \"recipent\": \"+16500123456\",\n"
				+ "                  \"message\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": []\n" + "            }\n"
				+ "          ]\n" + "        }\n" + "                  ]\n" + "                }\n"
				+ "              ]\n" + "            }\n" + "          ]\n" + "        },\n" + "        {\n"
				+ "          \"data\": {\n" + "            \"edgeClass\": \"_True\",\n"
				+ "            \"edgeProperties\": {},\n" + "            \"nodeClass\": \"_Location\",\n"
				+ "            \"nodeProperties\": {\n" + "              \"name\": \"Location:At\",\n"
				+ "              \"sid\": \"Location\",\n" + "              \"cid\": \"At\",\n"
				+ "              \"unit\": \"Default\",\n" + "              \"operator\": \"eq\",\n"
				+ "              \"value\": \"Location-Id-001\"\n" + "            }\n" + "          },\n"
				+ "          \"children\": [\n" + "            {\n" + "              \"data\": {\n"
				+ "                \"edgeClass\": \"_True\",\n" + "                \"edgeProperties\": {},\n"
				+ "                \"nodeClass\": \"_DwellTime\",\n" + "                \"nodeProperties\": {\n"
				+ "                  \"name\": \"DwellTime:High\",\n" + "                  \"sid\": \"DwellTime\",\n"
				+ "                  \"cid\": \"High\",\n" + "                  \"unit\": \"Default\",\n"
				+ "                  \"operator\": \"gteq\",\n" + "                  \"value\": \"20\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": [\n"
				+ "                {\n" + "                  \"data\": {\n"
				+ "                    \"edgeClass\": \"_True\",\n" + "                    \"edgeProperties\": {},\n"
				+ "                    \"nodeClass\": \"_Temperature\",\n"
				+ "                    \"nodeProperties\": {\n"
				+ "                      \"name\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4:37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"sid\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"cid\": \"37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"unit\": \"F\",\n" + "                      \"operator\": \"gt\",\n"
				+ "                      \"value\": \"92\"\n" + "                    }\n" + "                  },\n"
				+ "                  \"children\": [\n" + "                    {\n"
				+ "                      \"data\": {\n" + "                        \"edgeClass\": \"_True\",\n"
				+ "                        \"edgeProperties\": {},\n"
				+ "                        \"nodeClass\": \"_Humidity\",\n"
				+ "                        \"nodeProperties\": {\n"
				+ "                          \"name\": \"AA20:AA21\",\n"
				+ "                          \"sid\": \"AA20\",\n" + "                          \"cid\": \"AA21\",\n"
				+ "                          \"unit\": \"%\",\n" + "                          \"operator\": \"gt\",\n"
				+ "                          \"value\": \"40\"\n" + "                        }\n"
				+ "                      },\n" + "                      \"children\": [\n"
				+ "                      	{\n" + "          \"data\": {\n"
				+ "            \"edgeClass\": \"_Action\",\n" + "            \"edgeProperties\": {},\n"
				+ "            \"nodeClass\": \"_Webhook\",\n" + "            \"nodeProperties\": {\n"
				+ "              \"endpoint\": \"http://scn.cloudleaf.io/api/webhook\",\n"
				+ "              \"message\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "            }\n" + "          },\n" + "          \"children\": [\n" + "            {\n"
				+ "              \"data\": {\n" + "                \"edgeClass\": \"_Action\",\n"
				+ "                \"edgeProperties\": {},\n" + "                \"nodeClass\": \"_Sms\",\n"
				+ "                \"nodeProperties\": {\n" + "                  \"recipent\": \"+16500123456\",\n"
				+ "                  \"message\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": []\n" + "            }\n"
				+ "          ]\n" + "        }\n" + "                      ]\n" + "                    }\n"
				+ "                  ]\n" + "                }\n" + "              ]\n" + "            },\n"
				+ "            {\n" + "              \"data\": {\n" + "                \"edgeClass\": \"_True\",\n"
				+ "                \"edgeProperties\": {},\n" + "                \"nodeClass\": \"_DwellTime\",\n"
				+ "                \"nodeProperties\": {\n" + "                  \"name\": \"DwellTime:High\",\n"
				+ "                  \"sid\": \"DwellTime\",\n" + "                  \"cid\": \"High\",\n"
				+ "                  \"unit\": \"Default\",\n" + "                  \"operator\": \"gteq\",\n"
				+ "                  \"value\": \"25\"\n" + "                }\n" + "              },\n"
				+ "              \"children\": [\n" + "                {\n" + "                  \"data\": {\n"
				+ "                    \"edgeClass\": \"_True\",\n" + "                    \"edgeProperties\": {},\n"
				+ "                    \"nodeClass\": \"_Temperature\",\n"
				+ "                    \"nodeProperties\": {\n"
				+ "                      \"name\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4:37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"sid\": \"37C7BB40-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"cid\": \"37C7BBA1-B06A-11E3-8000-B70F3AB862A4\",\n"
				+ "                      \"unit\": \"F\",\n" + "                      \"operator\": \"gt\",\n"
				+ "                      \"value\": \"90\"\n" + "                    }\n" + "                  },\n"
				+ "                  \"children\": [\n" + "                  	{\n" + "          \"data\": {\n"
				+ "            \"edgeClass\": \"_Action\",\n" + "            \"edgeProperties\": {},\n"
				+ "            \"nodeClass\": \"_Webhook\",\n" + "            \"nodeProperties\": {\n"
				+ "              \"endpoint\": \"http://scn.cloudleaf.io/api/webhook\",\n"
				+ "              \"message\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "            }\n" + "          },\n" + "          \"children\": [\n" + "            {\n"
				+ "              \"data\": {\n" + "                \"edgeClass\": \"_Action\",\n"
				+ "                \"edgeProperties\": {},\n" + "                \"nodeClass\": \"_Sms\",\n"
				+ "                \"nodeProperties\": {\n" + "                  \"recipent\": \"+16500123456\",\n"
				+ "                  \"message\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": []\n" + "            }\n"
				+ "          ]\n" + "        }\n" + "                  ]\n" + "                }\n"
				+ "              ]\n" + "            }\n" + "          ]\n" + "        }      \n" + "      ]\n"
				+ "    }\n" + "  ]\n" + "}";

		response = RestAPIUtility.createPostRequest(endPoint, requestBody);

		return response;
	}

	public static Response createSimpleRuleLocationEntry(String endPoint) {

		requestBody = "{\n" + "  \"data\": {\n" + "    \"nodeClass\": \"_Class\",\n" + "    \"nodeProperties\": {\n"
				+ "      \"all\": \"false\",\n" + "      \"classIds\": [\n" + "        \"Pallets\",\n"
				+ "        \"Bins\"\n" + "      ]\n" + "    }\n" + "  },\n" + "  \"children\": [\n" + "    {\n"
				+ "      \"data\": {\n" + "        \"nodeClass\": \"_Rule\",\n" + "        \"nodeProperties\": {\n"
				+ "          \"name\": \"Asset Entry at pallets and bins send email\",\n"
				+ "          \"ruleTemplateId\": \"d603aa7e-4325-40e1-90bc-c0c1be9a99e9\"\n" + "        },\n"
				+ "        \"edgeClass\": \"RuleEdge\",\n" + "        \"edgeProperties\": {\n"
				+ "          \"label\": \"Rule\"\n" + "        }\n" + "      },\n" + "      \"children\": [\n"
				+ "        {\n" + "          \"data\": {\n" + "            \"edgeClass\": \"_True\",\n"
				+ "            \"edgeProperties\": {},\n" + "            \"nodeClass\": \"_Location\",\n"
				+ "            \"nodeProperties\": {\n" + "              \"name\": \"Location:At\",\n"
				+ "              \"sid\": \"Location\",\n" + "              \"cid\": \"At\",\n"
				+ "              \"unit\": \"Default\",\n" + "              \"operator\": \"eq\",\n"
				+ "              \"value\": \"Location-Id-001\"\n" + "            }\n" + "          },\n"
				+ "          \"children\": [\n" + "            {\n" + "              \"data\": {\n"
				+ "                \"edgeClass\": \"_Action\",\n" + "                \"edgeProperties\": {},\n"
				+ "                \"nodeClass\": \"_Webhook\",\n" + "                \"nodeProperties\": {\n"
				+ "                  \"endpoint\": \"http://scn.cloudleaf.io/api/webhook\",\n"
				+ "                  \"message\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": [\n"
				+ "                {\n" + "                  \"data\": {\n"
				+ "                    \"edgeClass\": \"_Action\",\n" + "                    \"edgeProperties\": {},\n"
				+ "                    \"nodeClass\": \"_Sms\",\n" + "                    \"nodeProperties\": {\n"
				+ "                      \"recipent\": \"+16500123456\",\n"
				+ "                      \"message\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "                    }\n" + "                  },\n" + "                  \"children\": []\n"
				+ "                }\n" + "              ]\n" + "            }\n" + "          ]\n" + "        }\n"
				+ "      ]\n" + "    }\n" + "  ]\n" + "}";

		response = RestAPIUtility.createPostRequest(endPoint, requestBody);

		return response;
	}

	public static Response CreateSimpleRuleLocationDwellTime(String endPoint) {

		requestBody = "{\n" + "  \"data\": {\n" + "    \"nodeClass\": \"_Class\",\n" + "    \"nodeProperties\": {\n"
				+ "      \"all\": \"false\",\n" + "      \"classIds\": [\n" + "        \"Pallets\",\n"
				+ "        \"Bins\"\n" + "      ]\n" + "    }\n" + "  },\n" + "  \"children\": [\n" + "    {\n"
				+ "      \"data\": {\n" + "        \"nodeClass\": \"_Rule\",\n" + "        \"nodeProperties\": {\n"
				+ "          \"name\": \"Asset Entry at pallets and bins send email\",\n"
				+ "          \"ruleTemplateId\": \"d603aa7e-4325-40e1-90bc-c0c1be9a99e9\"\n" + "        },\n"
				+ "        \"edgeClass\": \"RuleEdge\",\n" + "        \"edgeProperties\": {\n"
				+ "          \"label\": \"Rule\"\n" + "        }\n" + "      },\n" + "      \"children\": [\n"
				+ "        {\n" + "          \"data\": {\n" + "            \"edgeClass\": \"_True\",\n"
				+ "            \"edgeProperties\": {},\n" + "            \"nodeClass\": \"_Location\",\n"
				+ "            \"nodeProperties\": {\n" + "              \"name\": \"Location:At\",\n"
				+ "              \"sid\": \"Location\",\n" + "              \"cid\": \"At\",\n"
				+ "              \"unit\": \"Default\",\n" + "              \"operator\": \"eq\",\n"
				+ "              \"value\": \"Location-Id-001\"\n" + "            }\n" + "          },\n"
				+ "          \"children\": [\n" + "            {\n" + "              \"data\": {\n"
				+ "                \"edgeClass\": \"_True\",\n" + "                \"edgeProperties\": {},\n"
				+ "                \"nodeClass\": \"_DwellTime\",\n" + "                \"nodeProperties\": {\n"
				+ "                  \"name\": \"DwellTime:High\",\n" + "                  \"sid\": \"DwellTime\",\n"
				+ "                  \"cid\": \"High\",\n" + "                  \"unit\": \"Default\",\n"
				+ "                  \"operator\": \"gteq\",\n" + "                  \"value\": \"20\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": [\n"
				+ "                {\n" + "                  \"data\": {\n"
				+ "                    \"edgeClass\": \"_Action\",\n" + "                    \"edgeProperties\": {},\n"
				+ "                    \"nodeClass\": \"_Email\",\n" + "                    \"nodeProperties\": {\n"
				+ "                      \"recipent\": \"admin@cloudleaf.io\",\n"
				+ "                      \"subject\": \"Attention !! All the trigger points set, are blown!!\",\n"
				+ "                      \"body\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "                    }\n" + "                  },\n" + "                  \"children\": []\n"
				+ "                }\n" + "              ]\n" + "            }\n" + "          ]\n" + "        }\n"
				+ "      ]\n" + "    }\n" + "  ]\n" + "}";

		response = RestAPIUtility.createPostRequest(endPoint, requestBody);

		return response;
	}

	public static Response UpdateNode(String endPoint, String updatedName) {

		requestBody = "{\n" + "    \"name\": \"" + updatedName + "\"\n" + "}";

		response = RestAPIUtility.createPUTRequest(endPoint, requestBody);

		return response;
	}

	public static Response updateRelationShip(String endPoint, String modifiedBy) {

		requestBody = "{\n" + "	\n" + "	\"modified_by\":\"" + modifiedBy + "\"\n" + "}";

		response = RestAPIUtility.createPUTRequest(endPoint, requestBody);

		return response;
	}

	public static Response createRule(String endPoint) {

		requestBody = "{\n" + "  \"data\": {\n" + "    \"nodeClass\": \"_Class\",\n" + "    \"nodeProperties\": {\n"
				+ "      \"all\": \"false\",\n" + "      \"classIds\": [\n" + "        \"Pallets\",\n"
				+ "        \"Bins\"\n" + "      ]\n" + "    }\n" + "  },\n" + "  \"children\": [\n" + "    {\n"
				+ "      \"data\": {\n" + "        \"nodeClass\": \"_Rule\",\n" + "        \"nodeProperties\": {\n"
				+ "          \"name\": \"Asset Entry at pallets and bins send email\",\n"
				+ "          \"ruleTemplateId\": \"d603aa7e-4325-40e1-90bc-c0c1be9a99e9\"\n" + "        },\n"
				+ "        \"edgeClass\": \"RuleEdge\",\n" + "        \"edgeProperties\": {\n"
				+ "          \"label\": \"Rule\"\n" + "        }\n" + "      },\n" + "      \"children\": [\n"
				+ "        {\n" + "          \"data\": {\n" + "            \"edgeClass\": \"_True\",\n"
				+ "            \"edgeProperties\": {},\n" + "            \"nodeClass\": \"_Location\",\n"
				+ "            \"nodeProperties\": {\n" + "              \"name\": \"Location:At\",\n"
				+ "              \"sid\": \"Location\",\n" + "              \"cid\": \"At\",\n"
				+ "              \"unit\": \"Default\",\n" + "              \"operator\": \"eq\",\n"
				+ "              \"value\": \"Location-Id-001\"\n" + "            }\n" + "          },\n"
				+ "          \"children\": [\n" + "            {\n" + "              \"data\": {\n"
				+ "                \"edgeClass\": \"_True\",\n" + "                \"edgeProperties\": {},\n"
				+ "                \"nodeClass\": \"_DwellTime\",\n" + "                \"nodeProperties\": {\n"
				+ "                  \"name\": \"DwellTime:High\",\n" + "                  \"sid\": \"DwellTime\",\n"
				+ "                  \"cid\": \"High\",\n" + "                  \"unit\": \"Default\",\n"
				+ "                  \"operator\": \"gteq\",\n" + "                  \"value\": \"20\"\n"
				+ "                }\n" + "              },\n" + "              \"children\": [\n"
				+ "                {\n" + "                  \"data\": {\n"
				+ "                    \"edgeClass\": \"_Action\",\n" + "                    \"edgeProperties\": {},\n"
				+ "                    \"nodeClass\": \"_Email\",\n" + "                    \"nodeProperties\": {\n"
				+ "                      \"recipent\": \"admin@cloudleaf.io\",\n"
				+ "                      \"subject\": \"Attention !! All the trigger points set, are blown!!\",\n"
				+ "                      \"body\": \"Attention !! All the trigger points set, are blown!!\"\n"
				+ "                    }\n" + "                  },\n" + "                  \"children\": []\n"
				+ "                }\n" + "              ]\n" + "            }\n" + "          ]\n" + "        }\n"
				+ "      ]\n" + "    }\n" + "  ]\n" + "}";

		response = RestAPIUtility.createPostRequest(endPoint, requestBody);

		return response;
	}
}
