package testcases;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import models.devices.DeviceNotes;
import models.devices.DeviceTag;
import org.junit.runner.RunWith;
import utils.BaseTest;

import java.io.IOException;
import java.util.Collections;

import static constants.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.apache.http.HttpStatus.*;

@RunWith(SerenityRunner.class)
public class DeviceDetailsTest extends BaseTest {

    public static String CROWDSTRIKE_DEVICE_ID = "";
    public static String AZURE_CONTAINER_DEVICE_ID = "";
    public static String AZURE_LOADBALANCER_DEVICE_ID = "";
    public static String AZURE_PRIVATE_DNS_ZONE_DEVICE_ID = "";
    public static String AZURE_PUBLIC_DNS_ZONE_DEVICE_ID = "";
    public static String AZURE_APPLICATION_GATEWAY_DEVICE_ID = "";
    public static String AZURE_NETWORK_SECURITY_GROUP_DEVICE_ID = "";

    static {
        try {
            CROWDSTRIKE_DEVICE_ID = getIdFromURL("http://inventaserver:9092/query/devices/?query=((adapters.adapter_crowdstrike.status%20==%20exists(true))and(adapters.adapter_crowdstrike.hostname%20==%20%22TESTBENCH%22))");
            //http://inventaserver:9092/query/devices/?size=1&query=(adapters.adapter_crowdstrike==exists(true))&sort=_id,desc
            AZURE_CONTAINER_DEVICE_ID = getIdFromURL("http://inventaserver:9092/query/devices/?query=(adapters.adapter_azure.Container%20Instance::Status%20==%20exists(true))&page=0&size=1");
            AZURE_LOADBALANCER_DEVICE_ID = getIdFromURL("http://inventaserver:9092/query/devices/?query=(adapters.adapter_azure.Load%20Balancer::Type%20==%20exists(true))&page=0&size=1");
            AZURE_PRIVATE_DNS_ZONE_DEVICE_ID = getIdFromURL("http://inventaserver:9092/query/devices/?query=(adapters.adapter_azure.Private%20DNS%20Zones::Id%20==%20exists(true))&page=0&size=1");
            AZURE_PUBLIC_DNS_ZONE_DEVICE_ID = getIdFromURL("http://inventaserver:9092/query/devices/?query=(adapters.adapter_azure.Public%20DNS%20Zones::Type%20==%20exists(true))&page=0&size=1");
            AZURE_APPLICATION_GATEWAY_DEVICE_ID = getIdFromURL("http://inventaserver:9092/query/devices/?query=(common.hostName%20==%20%22AppGwPubIP%22)&page=0&size=1");
            AZURE_NETWORK_SECURITY_GROUP_DEVICE_ID = getIdFromURL("http://inventaserver:9092/query/devices/?query=(adapters.adapter_azure.Network%20Security%20Group::Type%20==%20exists(true))&page=0&size=1");

            //http://inventaserver:9092/devices/getAllDevices?page=0&size=1&sort=firstFetchTime,desc
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Title("Get All Device List")
    public void getAllDevices() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + GET_ALL_DEVICES).
                then().
                spec(responseSpec).
                and().
                body("data.content[0]._id", equalTo(DEVICE_DETAIL_ID));
    }

    @Test
    @Title("Get Device Details By Device Id")
    public void getDevicesDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + DEVICES_DETAILS + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec).
                and().
                body("data._id", equalTo(DEVICE_DETAIL_ID));
    }

    @Test
    @Title("Get Device with Connectors List By Device Id")
    public void getDeviceConnectorsList() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + CONNECTOR_LIST_BY_ID + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Hard Drive Details By Device Id")
    public void getDrivesDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + DRIVE_DETAILS + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Operating System Patches Details By Device Id")
    public void getOSPatchesDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + OS_PATCHES + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);

    }

    @Test
    @Title("Get Running Processes Details By Device Id")
    public void getRunningProcessesDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + RUNNING_PROCESSES + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Installed Software Details By Device Id")
    public void getInstalledSoftwareDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + INSTALLED_SOFTWARE + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Installed Software Details By Device Id")
    public void getOSDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + OS_INFO + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Device User Details By Device Id")
    public void getUserDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + USER_DETAILS_BY_ID + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Network Interface Details By Device Id")
    public void getNetworkInterfacesDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + NETWORK_INTERFACE + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Shared Folders Details By Device Id")
    public void getSharedFoldersDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + SHARED_FOLDER + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Device Note By Device Id")
    public void getDeviceNoteDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + DEVICE_NOTE + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Device Tags By Device Id")
    public void getDeviceTagDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + DEVICE_TAG + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    @Test //extra
    @Title("Get General Details By Device Id")
    public void getGeneralDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + GENERAL_DETAILS + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec).
                and().
                body("data._id", equalTo(DEVICE_DETAIL_ID));
    }

    @Test
    @Title("Get Connector Details By Device Id")
    public void getConnectorDetailsById() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + CONNECTOR_DATA + DEVICE_DETAIL_ID + "&adapter").
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Post Insert Tags on Device")
    public void postInsertDeviceTag() {
        DeviceTag deviceTag = new DeviceTag("Automation_Device_Tag_Number_" + value + "1", Collections.singletonList(DEVICE_DETAIL_ID));
        given().
                spec(requestSpec).
                and().
                body(deviceTag).
                when().
                post(DEVICE_ENDPOINT + INSERT_TAG).
                then().
                assertThat().
                statusCode(SC_OK);
    }

    @Test
    @Title("Post Insert Note on Device")
    public void postInsertDeviceNote() {
        DeviceNotes deviceNotes = new DeviceNotes("Automation_Notes_#_" + value + "", "" + DEVICE_DETAIL_ID);
        given().
                spec(requestSpec).
                and().
                body(deviceNotes).
                when().
                post(DEVICE_ENDPOINT + INSERT_NOTE).
                then().
                assertThat().
                statusCode(SC_OK);
    }

    @Test
    @Title("Get All Device Tags")
    public void getAllTags() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + ALL_TAGS).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Load Balancer Rules Details By Device Id")
    public void getLoadBalancerRulesForDevice() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + LOAD_BALANCER_RULE + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    @Ignore
    @Test // Not throw exception if tag name not exist.
    @Title("Delete Device Single Tag")
    public void deleteDeviceSingleTag() {
        //BASE_ENDPOINT_INVENTA + DEVICE_ENDPOINT + DELETE_SINGLE_TAG + DEVICE_DETAIL_ID +"&tag="+ SINGLE_TAG_NAME;


    }

    @Ignore
    @Test // issue - not complete. also postman issue
    @Title("Delete Device Bulk Tag")
    public void deleteDeviceBukTag() {
        //BASE_ENDPOINT_INVENTA + DEVICE_ENDPOINT + DELETE_BULK_TAG


    }

    @Ignore
    @Test // Not throw exception if note does not exist.
    @Title("Delete Device Note Tag")
    public void deleteDeviceNote() {
        //BASE_ENDPOINT_INVENTA + DEVICE_ENDPOINT + DELETE_NOTE + DEVICE_DETAIL_ID

    }

    @Ignore
    @Test // Not throw exception if note does not exist.
    @Title("Delete Discovered Devices")
    public void deleteDiscoveredDevice() throws IOException {
        //BASE_ENDPOINT_INVENTA + DEVICE_ENDPOINT + DELETE_DEVICE + DELETE_DEVICE_ID
    }

    @Test
    @Title("Get Hardware Details By Device Id")
    public void getConnectedHardwareForDevice() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + DEVICE_HARDWARE + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get User for Device Details By Device Id")
    public void getUsersForDevice() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + USER_DETAILS_BY_ID + DEVICE_DETAIL_ID).
                then().
                spec(responseSpec);
    }

    /**
     * Adapter Wise Test Cases
     */

    @Test
    @Title("Get CrowdStrike Incident Details By Device Id")
    public void getCrowdStrikeIncident() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + CROWDSTRIKE_INCIDENTS + CROWDSTRIKE_DEVICE_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get CrowdStrike Sensor Update Policy Details By Device Id")
    public void getCrowdStrikeSensorUpdatePolicy() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + CROWDSTRIKE_SENSOR_UPDATE_POLICY + CROWDSTRIKE_DEVICE_ID).
                then().
                spec(responseSpec);
    }
    @Test
    @Title("Get CrowdStrike Prevention Policies Details By Device Id")
    public void getCrowdStrikePreventionPolicies() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + CROWDSTRIKE_PREVENTION_POLICIES + CROWDSTRIKE_DEVICE_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Azure Container Protocol Details By Device Id")
    public void getAzureContainerProtocolDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + CONTAINER_PROTOCOLS + AZURE_CONTAINER_DEVICE_ID).
                then().
                spec(responseSpec);
    }
    @Test
    @Title("Get Azure Container Port Details By Device Id")
    public void getAzureContainerPortDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + CONTAINER_PORTS + AZURE_CONTAINER_DEVICE_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Azure Container In-Depth Details By Device Id")
    public void getAzureContainerInDepthDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + CONTAINER_INDEPTH + AZURE_CONTAINER_DEVICE_ID).
                then().
                spec(responseSpec);
    }
    @Test
    @Title("Get Azure Container Tag Details By Device Id")
    public void getAzureContainerTagsDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + ASSETS_TAGS + AZURE_CONTAINER_DEVICE_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Azure Load Balancer Rule Details By Device Id")
    public void getAzureLoadBalancerRuleDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + LOAD_BALANCER_RULES + AZURE_LOADBALANCER_DEVICE_ID).
                then().
                spec(responseSpec);
    }
    @Test
    @Title("Get Azure Backend Pool Details By Device Id")
    public void getAzureBackendPoolDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + BACKEND_POOL + AZURE_LOADBALANCER_DEVICE_ID).
                then().
                spec(responseSpec);
    }
    @Test
    @Title("Get Azure Private Host Zone Details By Device Id")
    public void getAzurePrivateHostZoneDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + HOST_ZONE + AZURE_PRIVATE_DNS_ZONE_DEVICE_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Azure Public Host Zone Details By Device Id")
    public void getAzurePublicHostZoneDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + HOST_ZONE + AZURE_PUBLIC_DNS_ZONE_DEVICE_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Azure Application Gateway Health Probe Details By Device Id")
    public void getAzureApplicationGatewayHealthProbeDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + HEALTH_PROBE + AZURE_APPLICATION_GATEWAY_DEVICE_ID).
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Azure Application Gateway HTTP Setting Details By Device Id")
    public void getAzureApplicationGatewayHttpSettingDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + HTTP_SETTING + AZURE_APPLICATION_GATEWAY_DEVICE_ID).
                then().
                spec(responseSpec);
    }
    @Test
    @Title("Get Azure Application Gateway Rules Details By Device Id")
    public void getAzureApplicationGatewayRulesDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + GATEWAY_RULES + AZURE_APPLICATION_GATEWAY_DEVICE_ID).
                then().
                spec(responseSpec);
    }
    @Test
    @Title("Get Azure Network Security Group Firewall In Bound Rules Details By Device Id")
    public void getAzureNSGFirewallInBoundRulesDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + FIREWALL_RULES + AZURE_NETWORK_SECURITY_GROUP_DEVICE_ID + "&direction=Inbound").
                then().
                spec(responseSpec);
    }
    @Test
    @Title("Get Azure Network Security Group Firewall Out Bound Rules Details By Device Id")
    public void getAzureNSGFirewallOutBoundRulesDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + FIREWALL_RULES + AZURE_NETWORK_SECURITY_GROUP_DEVICE_ID + "&direction=Outbound").
                then().
                spec(responseSpec);
    }

    @Test
    @Title("Get Azure Network Security Group Connected Hardware Details By Device Id")
    public void getAzureNSGConnectedHardwareDetails() {
        given().
                spec(requestSpec).
                when().
                get(DEVICE_ENDPOINT + CONNECTED_HARDWARE + AZURE_NETWORK_SECURITY_GROUP_DEVICE_ID ).
                then().
                spec(responseSpec);
    }
}
