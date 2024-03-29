package com.example.demo.util;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RequestUtil {
    public static void main(String[] args) {
        RestTemplate restTemplate=new RestTemplate();

        List<String> list = Arrays.asList("24931024",
                "24783504",
                "24930389",
                "24930389",
                "24930389",
                "24930556",
                "24930529",
                "24930466",
                "24930466",
                "24930466",
                "24930466",
                "24930466",
                "24929279",
                "24929758",
                "24929606",
                "24929258",
                "24928582",
                "24927765",
                "24927400",
                "24927451",
                "24927756",
                "24927685",
                "24927505",
                "24926660",
                "24926568",
                "24925910",
                "24924615",
                "24923895",
                "24921659",
                "24921646",
                "24921373",
                "24921331",
                "24921152",
                "24921095",
                "24920799",
                "24920406",
                "24919418",
                "24919156",
                "24918268",
                "24918478",
                "24918034",
                "24917740",
                "24917690",
                "24917603",
                "24917272",
                "24917356",
                "24916463",
                "24915470",
                "24913910",
                "24899934",
                "24898306",
                "24899111",
                "24898326",
                "24897657",
                "24897351",
                "24896667",
                "24897693",
                "24896168",
                "24895754",
                "24895382",
                "24895293",
                "24895308",
                "24894993",
                "24894321",
                "24894113",
                "24893969",
                "24893995",
                "24893096",
                "24893115",
                "24893129",
                "24891952",
                "24892545",
                "24892155",
                "24891692",
                "24890342",
                "24891085",
                "24890895",
                "24889031",
                "24889404",
                "24890003",
                "24889930",
                "24889883",
                "24889695",
                "24889481",
                "24889724",
                "24889573",
                "24889047",
                "24889192",
                "24888798",
                "24887639",
                "24888418",
                "24888604",
                "24887548",
                "24887788",
                "24887830",
                "24887548",
                "24887466",
                "24887440",
                "24886458",
                "24886438",
                "24886208",
                "24886198",
                "24886216",
                "24886062",
                "24885698",
                "24885251",
                "24885624",
                "24885884",
                "24885475",
                "24885527",
                "24885236",
                "24883670",
                "24884925",
                "24883643",
                "24883571",
                "24883614",
                "24883594",
                "24883276",
                "24883276",
                "24883268",
                "24883186",
                "24882480",
                "24880956",
                "24879634",
                "24872202",
                "24867960",
                "24866173",
                "24864865",
                "24864942",
                "24864087",
                "24863565",
                "24864204",
                "24863489",
                "24863859",
                "24863749",
                "24862546",
                "24862808",
                "24863488",
                "24862638",
                "24863268",
                "24862482",
                "24863071",
                "24862980",
                "24862215",
                "24861713",
                "24862074",
                "24860432",
                "24860352",
                "24861652",
                "24861731",
                "24861237",
                "24861497",
                "24860564",
                "24861207",
                "24861192",
                "24860932",
                "24860891",
                "24860584",
                "24860631",
                "24859987",
                "24859952",
                "24859570",
                "24859352",
                "24859280",
                "24859225",
                "24858895",
                "24859026",
                "24858899",
                "24858765",
                "24858524",
                "24858329",
                "24858284",
                "24858207",
                "24858092",
                "24858074",
                "24857983",
                "24857883",
                "24857838",
                "24857746",
                "24857650",
                "24857531",
                "24857591",
                "24857372",
                "24857232",
                "24857188",
                "24856858",
                "24857053",
                "24856857",
                "24856538",
                "24856418",
                "24856238",
                "24855387",
                "24855511",
                "24855635",
                "24855267",
                "24855357",
                "24855054",
                "24854264",
                "24854550",
                "24854896",
                "24854647",
                "24854489",
                "24854341",
                "24854136",
                "24854331",
                "24854264",
                "24853875",
                "24853249",
                "24853593",
                "24853376",
                "24853844",
                "24853767",
                "24852248",
                "24852931",
                "24852060",
                "24852625",
                "24851594",
                "24852504",
                "24852435",
                "24852249",
                "24851475",
                "24851765",
                "24851980",
                "24851966",
                "24850746",
                "24851011",
                "24850904",
                "24850785",
                "24850805",
                "24851288",
                "24851128",
                "24851011",
                "24851017",
                "24850904",
                "24850298",
                "24850265",
                "24849039",
                "24848764",
                "24834439",
                "24834610",
                "24833955",
                "24833183",
                "24831520",
                "24830986",
                "24830861",
                "24830150",
                "24830353",
                "24830279",
                "24829570",
                "24829380",
                "24828667",
                "24828641",
                "24828061",
                "24828254",
                "24827680",
                "24827112",
                "24827901",
                "24827861",
                "24827605",
                "24827492",
                "24827319",
                "24825452",
                "24827164",
                "24826282",
                "24825755",
                "24824803",
                "24824593",
                "24824733",
                "24824525",
                "24823148",
                "24823015",
                "24822988",
                "24822609",
                "24822413",
                "24822566",
                "24822522",
                "24822410",
                "24821942",
                "24822002",
                "24821644",
                "24821694",
                "24821259",
                "24820997",
                "24820918",
                "24820854",
                "24820767",
                "24820692",
                "24820453",
                "24820464",
                "24820152",
                "24819466",
                "24819439",
                "24819466",
                "24819176",
                "24819267",
                "24818802",
                "24819131",
                "24818800",
                "24818794",
                "24818461",
                "24818349",
                "24818283",
                "24818252",
                "24818245",
                "24818230",
                "24817740",
                "24817931",
                "24810703",
                "24803645",
                "24801805",
                "24801247",
                "24801159",
                "24799643",
                "24798887",
                "24797575",
                "24797035",
                "24796557",
                "24796655",
                "24796763",
                "24796188",
                "24796623",
                "24796557",
                "24796244",
                "24796200",
                "24795890",
                "24795786",
                "24795683",
                "24795377",
                "24794619",
                "24794495",
                "24794343",
                "24794162",
                "24794108",
                "24793184",
                "24793160",
                "24792953",
                "24791587",
                "24792047",
                "24791567",
                "24791319",
                "24791159",
                "24791096",
                "24790607",
                "24790824",
                "24790863",
                "24790810",
                "24788744",
                "24788216",
                "24789799",
                "24788344",
                "24789055",
                "24789569",
                "24789391",
                "24788618",
                "24789042",
                "24788618",
                "24787659",
                "24788429",
                "24787524",
                "24787285",
                "24787269",
                "24787223",
                "24787132",
                "24787234",
                "24786915",
                "24786590",
                "24785505",
                "24783504",
                "24783677",
                "24783567",
                "24783293",
                "24783345",
                "24783236",
                "24781520",
                "24781294",
                "24769028",
                "24768887",
                "24769058",
                "24768586",
                "24767484",
                "24768050",
                "24766249",
                "24765389",
                "24765370",
                "24765179",
                "24764252",
                "24764976",
                "24764694",
                "24763886",
                "24761289",
                "24761289",
                "24763748",
                "24763760",
                "24764293",
                "24764114",
                "24761289",
                "24763924",
                "24763882",
                "24763721",
                "24763684",
                "24763563",
                "24763374",
                "24763352",
                "24763106",
                "24763086",
                "24762425",
                "24762024",
                "24762072",
                "24761971",
                "24761847",
                "24761289",
                "24761147",
                "24761095",
                "24760905",
                "24760725",
                "24760394",
                "24760295",
                "24759784",
                "24759254",
                "24759345",
                "24759293",
                "24759016",
                "24758874",
                "24757794",
                "24758177",
                "24757281",
                "24756831",
                "24756743",
                "24756916",
                "24756833",
                "24756796",
                "24756262",
                "24756060",
                "24756060",
                "24755683",
                "24755731",
                "24754924",
                "24755394",
                "24755142",
                "24754455",
                "24755148",
                "24754587",
                "24754576",
                "24754362",
                "24754509",
                "24754398",
                "24754455",
                "24754335",
                "24754253",
                "24753363",
                "24752208",
                "24750908",
                "24735196",
                "24732974",
                "24732823",
                "24732523",
                "24732562",
                "24732180",
                "24731622",
                "24730430",
                "24730430",
                "24729373",
                "24729364",
                "24728944",
                "24729059",
                "24729135",
                "24729079",
                "24728706",
                "24728600",
                "24728328",
                "24728180",
                "24728166",
                "24728113",
                "24727355");


        for (String s1 : list) {
            String s="https://www.sayweee.com/api_invite_v1/api_invite_rebate?order_id="+s1;
            Object object = restTemplate.getForObject(s, Object.class);
            System.out.println(object);
        }
    }
}
