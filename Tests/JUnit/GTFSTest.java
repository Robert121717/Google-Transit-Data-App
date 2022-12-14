package JUnit;

import GTFS.GTFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GTFSTest {

    private GTFS gtfs;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        gtfs = new GTFS();
    }


    /**
     * @author Cody Morrow
     * This method tests the verifyRouteHeader method in GTFS class
     */
    @org.junit.jupiter.api.Test
    void verifyRouteHeader() {
        String trueHeader = "route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color,route_text_color";
        assertTrue(gtfs.verifyRouteHeader(trueHeader));

        String missingRouteId = "agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color,route_text_color";
        assertFalse(gtfs.verifyRouteHeader(missingRouteId));

        String missingAgencyId = "route_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color,route_text_color";
        assertFalse(gtfs.verifyRouteHeader(missingAgencyId));

        String missingRouteShort = "route_id,agency_id,route_long_name,route_desc,route_type,route_url,route_color,route_text_color";
        assertFalse(gtfs.verifyRouteHeader(missingRouteShort));

        String missingRouteLong = "route_id,agency_id,route_short_name,route_desc,route_type,route_url,route_color,route_text_color";
        assertFalse(gtfs.verifyRouteHeader(missingRouteLong));

        String missingRouteDesc = "route_id,agency_id,route_short_name,route_long_name,route_type,route_url,route_color,route_text_color";
        assertFalse(gtfs.verifyRouteHeader(missingRouteDesc));

        String missingRouteType = "route_id,agency_id,route_short_name,route_long_name,route_desc,route_url,route_color,route_text_color";
        assertFalse(gtfs.verifyRouteHeader(missingRouteType));

        String missingRouteURL = "route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_color,route_text_color";
        assertFalse(gtfs.verifyRouteHeader(missingRouteURL));

        String missingRouteColor = "route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_text_color";
        assertFalse(gtfs.verifyRouteHeader(missingRouteColor));

        String missingTextColor = "route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color";
        assertFalse(gtfs.verifyRouteHeader(missingTextColor));
    }

    /**
     * @author Robert Schmidt
     * This method tests the verifyStopTimeHeader method in the GTFS class.
     * The method tested should check the header format of any stop time files that are imported
     *          to the GTFS database and return true if the header is formatted properly.
     */
    @org.junit.jupiter.api.Test
    void verifyStopTimeHeader() {
        String expectedHeader = "trip_id,arrival_time,departure_time,stop_id,stop_sequence," +
                "stop_headsign,pickup_type,drop_off_type";
        assertTrue(gtfs.verifyStopTimeHeader(expectedHeader));

        // removes 3 chars from the expected header at a time and checks it doesn't pass
        // as being properly formatted with the remaining header attributes.
        for (int i = 1; i < expectedHeader.length() - 3; i += 3) {
            String test = expectedHeader.substring(i);
            assertFalse(gtfs.verifyStopTimeHeader(test));
        }

        // randomly shuffles the attributes in the string and checks the resulting string still doesn't pass
        // as being properly formatted.
        List<String> originalAttributes = new ArrayList<>(List.of(expectedHeader.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)));
        List<String> attributes = new ArrayList<>(originalAttributes);

        for (int i = 0; i < attributes.size(); ++i) {
            Collections.shuffle(attributes);
            // ensure the list wasn't shuffled back into its original order.
            while (attributes.equals(originalAttributes)) {
                Collections.shuffle(attributes);
            }
            StringBuilder sb = new StringBuilder();
            for (String attribute : attributes) {
                sb.append(attribute).append(",");
            }
            assertFalse(gtfs.verifyStopTimeHeader(sb.toString()));
        }

        // removes attributes from the header one at a time and checks that the resulting string does not pass
        // as being properly formatted.
        for (int i = 0; i < attributes.size(); ++i) {
            List<String> testList = new ArrayList<>(originalAttributes);
            testList.remove(i);

            StringBuilder sb = new StringBuilder();
            for (String attribute : testList) {
                sb.append(attribute).append(",");
            }
            assertFalse(gtfs.verifyStopTimeHeader(sb.toString()));
        }
    }

    /**
     * @author Achuth Nair
     * This method tests the verifyStopHeader method in the GTFS class
     */
    @org.junit.jupiter.api.Test
    void verifyStopHeader() {
        String trueHeader = "stop_id,stop_name,stop_desc,stop_lat,stop_lon";
        assertTrue(gtfs.verifyStopHeader(trueHeader));

        String missingStopId = "stop_name,stop_desc,stop_lat,stop_lon";
        assertFalse(gtfs.verifyTripHeader(missingStopId));

        String missingStopName = "stop_id,,stop_desc,stop_lat,stop_lon";
        assertFalse(gtfs.verifyTripHeader(missingStopName));

        String missingStopDesc = "stop_id,stop_name,,stop_lat,stop_lon";
        assertFalse(gtfs.verifyTripHeader(missingStopDesc));

        String missingStopLat = "stop_id,stop_name,stop_desc,,stop_lon";
        assertFalse(gtfs.verifyTripHeader(missingStopLat));

        String missingStopLon = "stop_id,stop_name,stop_desc,stop_lat";
        assertFalse(gtfs.verifyTripHeader(missingStopLon));
    }

    /**
     * @author Achuth Nair
     * This method tests the verifyTripHeader method in the GTFS class
     */
    @org.junit.jupiter.api.Test
    void verifyTripHeader() {
        String trueHeader = "route_id,service_id,trip_id,trip_headsign,direction_id,block_id,shape_id";
        assertTrue(gtfs.verifyTripHeader(trueHeader));

        String missingRouteId = "service_id,trip_id,trip_headsign,direction_id,block_id,shape_id";
        assertFalse(gtfs.verifyTripHeader(missingRouteId));

        String missingServiceId = "route_id,,trip_id,trip_headsign,direction_id,block_id,shape_id";
        assertFalse(gtfs.verifyTripHeader(missingServiceId));

        String missingTripId = "route_id,service_id,,trip_headsign,direction_id,block_id,shape_id";
        assertFalse(gtfs.verifyTripHeader(missingTripId));

        String missingTripHeadsign = "route_id,service_id,trip_id,,direction_id,block_id,shape_id";
        assertFalse(gtfs.verifyTripHeader(missingTripHeadsign));

        String missingDirectionId = "route_id,service_id,trip_id,trip_headsign,,block_id,shape_id";
        assertFalse(gtfs.verifyTripHeader(missingDirectionId));

        String missingBlockId = "route_id,service_id,trip_id,trip_headsign,direction_id,,shape_id";
        assertFalse(gtfs.verifyTripHeader(missingBlockId));

        String missingShapeId = "route_id,service_id,trip_id,trip_headsign,direction_id,block_id,";
        assertFalse(gtfs.verifyTripHeader(missingShapeId));
    }



    /**
     * @author Achuth Nair
     * This method tests the validateRouteData method in the GTFS class
     */
    @org.junit.jupiter.api.Test
    void validateRouteData() {

        String trueDataString = "12,MCTS,12,Teutonia-Hampton,,3,,008345,";
        String[] trueRouteDataTest = trueDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateRouteData(trueRouteDataTest));


        String shortDataString = "12,MCTS,12,";
        String[]  falseShortRouteDataTest = shortDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateRouteData(falseShortRouteDataTest));

        String longDataString = "12,MCTS,12,Teutonia-Hampton,,3,,008345,12,MCTS,12,Teutonia-Hampton,,3,,008345,";
        String[]  falseLongRouteDataTest = longDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateRouteData(falseLongRouteDataTest));

        String missingRouteTypeDataString = ",MCTS,12,Teutonia-Hampton,,3,,008345,";
        String[]  missingRouteTypeDataTest = missingRouteTypeDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateRouteData(missingRouteTypeDataTest));


        String missingRouteColorDataString = "12,MCTS,12,Teutonia-Hampton,,3,,,";
        String[]  missingRouteColorDataTest = missingRouteColorDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateRouteData(missingRouteColorDataTest));

        String invalidRouteColorDataString = "12,MCTS,12,Teutonia-Hampton,,3,,ZZZZZZ,";
        String[]  invalidRouteDataTest = invalidRouteColorDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateRouteData(invalidRouteDataTest));

        String invalidRouteTypeDataString = "12,MCTS,12,Teutonia-Hampton,,23,,008345,";
        String[] invalidRouteTypeDataTest = invalidRouteTypeDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateRouteData(invalidRouteTypeDataTest));

        String validRouteTextColor = "12,MCTS,12,Teutonia-Hampton,,3,,008345,FFFFFF";
        String[] validRouteTextColorDataTest = validRouteTextColor.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateRouteData(validRouteTextColorDataTest));

        String invalidRouteTextColor = "12,MCTS,12,Teutonia-Hampton,,3,,008345,ZZZZZZ";
        String[] invalidRouteTextColorDataTest = invalidRouteTextColor.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateRouteData(invalidRouteTextColorDataTest));


        String validRouteURLDataString = "12,MCTS,12,Teutonia-Hampton,,3,http://www.w3.org/albert/bertram/marie-claude,008345,";
        String[]  validRouteURLDataTest = validRouteURLDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateRouteData(validRouteURLDataTest));

        String invalidRouteURLDataString = "12,MCTS,12,Teutonia-Hampton,,3,fxqn:/us/va/reston/cnri/ietf/24/asdf%*.fred,008345,";
        String[]  invalidRouteURLDataTest = invalidRouteURLDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateRouteData(invalidRouteURLDataTest));
    }
    /**
     * @author Ryan Atkinson
     * This method tests the validateStopData method in the GTFS class
     */
    @org.junit.jupiter.api.Test
    void validateStopData() {
        String trueDataString = "6712,STATE & 5101 #6712,,43.0444475,-87.9779369";
        String[] trueStopDataTest = trueDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateStopData(trueStopDataTest));

        String shortDataString = "6712,STATE & 5101 #6712";
        String[] falseShortStopDataTest = shortDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopData(falseShortStopDataTest));

        String longDataString = "6712,STATE & 5101 #6712,,43.0444475,-87.9779369,33,44,55";
        String[] falseLongStopDataTest = longDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopData(falseLongStopDataTest));

        String missingStopID = ",STATE & 5101 #6712,,43.0444475,-87.9779369";
        String[] missingStopIDTest = missingStopID.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopData(missingStopIDTest));

        String missingLat = ",STATE & 5101 #6712,,,-87.9779369";
        String[] missingStopLat = missingLat.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopData(missingStopLat));

        String missingLon = ",STATE & 5101 #6712,,43.0444475,,";
        String[] missingStopLon = missingLon.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopData(missingStopLon));

        String invalidLargeLat = ",STATE & 5101 #6712,,90.111,-87.9779369";
        String[] invalidStopLargeLat = invalidLargeLat.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopData(invalidStopLargeLat));

        String invalidSmallLat = ",STATE & 5101 #6712,,-90.111,-87.9779369";
        String[] invalidStopSmallLat = invalidSmallLat.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopData(invalidStopSmallLat));

        String invalidLargeLon = ",STATE & 5101 #6712,,43.0444475,181.2737";
        String[] invalidStopLargeLon = invalidLargeLon.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopData(invalidStopLargeLon));

        String invalidSmallLon = ",STATE & 5101 #6712,,43.0444475,-181.2737";
        String[] invalidStopSmallLon = invalidSmallLon.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopData(invalidStopSmallLon));


    }

    /**
     * @author Achuth Nair
     * This method tests the validateTripData method in the GTFS class
     */
    @org.junit.jupiter.api.Test
    void validateTripData() {
        String trueDataString = "64,17-SEP_SUN,21736564_2535,60TH-VLIET,0,64102,17-SEP_64_0_23";
        String[] trueTripData = trueDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateTripData(trueTripData));

        String shortDataString = "64,17-SEP_SUN";
        String[] shortTripData = shortDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateTripData(shortTripData));

        String longDataString = "64,17-SEP_SUN,21736564_2535,60TH-VLIET,0,64102," +
                "17-SEP_64_0_23 64,17-SEP_SUN,21736564_2535,60TH-VLIET,0,64102,17-SEP_64_0_23";
        String[] longTripData = longDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateTripData(longTripData));

        String missingRouteId = ",17-SEP_SUN,21736564_2535,60TH-VLIET,0,64102,17-SEP_64_0_23";
        String[] missingRouteIdData = missingRouteId.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateTripData(missingRouteIdData));

        String missingTripId = "64,17-SEP_SUN,,60TH-VLIET,0,64102,17-SEP_64_0_23";
        String[] missingTripIdData = missingTripId.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateTripData(missingTripIdData));

        String trueDirectionId = "64,17-SEP_SUN,21736564_2535,60TH-VLIET,1,64102,17-SEP_64_0_23";
        String[] trueDirectionIdData = trueDirectionId.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateTripData(trueDirectionIdData));

        String trueMissingDirectionId = "64,17-SEP_SUN,21736564_2535,60TH-VLIET,,64102,17-SEP_64_0_23";
        String[] trueMissingDirectionIdData = trueMissingDirectionId.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateTripData(trueMissingDirectionIdData));

        String invalidDirectionId = "64,17-SEP_SUN,21736564_2535,60TH-VLIET,5,64102,17-SEP_64_0_23";
        String[] invalidDirectionIdData = invalidDirectionId.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateTripData(invalidDirectionIdData));
    }

    /**
     * @author Achuth Nair and Ryan Atkinson
     * This method tests the validateStopTimeData method in the GTFS class
     */
    @org.junit.jupiter.api.Test
    void validateStopTimeData() {
        String trueDataString = "21736564_2535,08:51:00,08:51:00,9113,1,,0,0";
        String[] trueStopTimeDataTest = trueDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateStopTimeData(trueStopTimeDataTest));

        String shortDataString = "21736564_2535,08:51:00";
        String[] falseShortStopTimeDataTest = shortDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(falseShortStopTimeDataTest));

        String longDataString = "21736564_2535,08:51:00,08:51:00,9113,1,,0,021736564_2535,08:51:00,08:51:00,9113,1,,0,0";
        String[] falseLongStopTimeDataTest = longDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(falseLongStopTimeDataTest));

        String missingStopIDDataString = "21736564_2535,08:51:00,08:51:00,,1,,0,0";
        String[] missingStopIDDataTest = missingStopIDDataString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(missingStopIDDataTest));

        String missingID = ",08:51:00,08:51:00,9113,1,,0,0";
        String[] missingTripID = missingID.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(missingTripID));

        String trueArrivalTime = "21736564_2535,26:51:00,08:51:00,9113,1,,0,0";
        String[] trueStopTimeArrivalTime = trueArrivalTime.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateStopTimeData(trueStopTimeArrivalTime));

        String invalidArrivalTimeMin = "21736564_2535,22:61:00,08:51:00,9113,1,,0,0";
        String[] invalidStopTimeArrivalTimeMin = invalidArrivalTimeMin.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(invalidStopTimeArrivalTimeMin));

        String invalidArrivalTimeSec = "21736564_2535,08:51:60,08:51:00,9113,1,,0,0";
        String[] invalidStopTimeArrivalTimeSec = invalidArrivalTimeSec.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(invalidStopTimeArrivalTimeSec));

        String invalidArrivalTimeFormat = "21736564_2535,6:51:30,08:51:00,9113,1,,0,0";
        String[] invalidStopTimeArrivalTimeFormat = invalidArrivalTimeFormat.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(invalidStopTimeArrivalTimeFormat));

        String trueDepartureTime = "21736564_2535,08:51:00,14:51:00,9113,1,,0,0";
        String[] trueStopTimeDepartureTime = trueDepartureTime.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateStopTimeData(trueStopTimeDepartureTime));

        String invalidDepartureTimeMin = "21736564_2535,08:51:00,14:71:00,9113,1,,0,0";
        String[] invalidStopTimeDepartureTimeMin = invalidDepartureTimeMin.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(invalidStopTimeDepartureTimeMin));

        String invalidDepartureTimeSec = "21736564_2535,08:51:00,08:51:60,9113,1,,0,0";
        String[] invalidStopTimeDepartureTimeSec= invalidDepartureTimeSec.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(invalidStopTimeDepartureTimeSec));

        String invalidDepartureTimeFormat = "21736564_2535,08:51:00,6:51:30,9113,1,,0,0";
        String[] invalidStopTimeDepartureTimeFormat = invalidDepartureTimeFormat.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(invalidStopTimeDepartureTimeFormat));

        String missingStopSequence = "21736564_2535,08:51:00,08:51:00,9113,,,0,0";
        String[] missingStopTimeSequence = missingStopSequence.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(missingStopTimeSequence));

        String invalidStopSequence = "21736564_2535,08:51:00,08:51:00,9113,-50,,0,0";
        String[] invalidStopTimeSequence = invalidStopSequence.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(invalidStopTimeSequence));

        String validStopSequence = "21736564_2535,08:51:00,08:51:00,9113,500,,0,0";
        String[] validStopTimeSequence = validStopSequence.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateStopTimeData(validStopTimeSequence));

        String validPickUpType = "21736564_2535,08:51:00,08:51:00,9113,500,,3,0";
        String[] validStopTimePickUpType = validPickUpType.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateStopTimeData(validStopTimePickUpType));

        String invalidPickUpType = "21736564_2535,08:51:00,08:51:00,9113,500,,20,0";
        String[] invalidStopTimePickUpType = invalidPickUpType.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(invalidStopTimePickUpType));

        String validDropOffType = "21736564_2535,08:51:00,08:51:00,9113,500,,0,2";
        String[] validStopTimeDropOffType = validDropOffType.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertTrue(gtfs.validateStopTimeData(validStopTimeDropOffType));

        String invalidDropOffType = "21736564_2535,08:51:00,08:51:00,9113,500,,0,50";
        String[] invalidStopTimeDropOffType = invalidDropOffType.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        assertFalse(gtfs.validateStopTimeData(invalidStopTimeDropOffType));
    }

}