package com.sardes.thegabworkproject.data.models

data class Language(
    val name: Name,
    val level: Level
) {

    sealed class Level(val level: String) {
        object A1 : Level("A1")
        object A2 : Level("A2")
        object B1 : Level("B1")
        object B2 : Level("B2")
        object C1 : Level("C1")
        object C2 : Level("C2")
    }


    sealed class Name(val name: String) {
        object Abenaki : Name("abenaki")
        object Afrikaans : Name("afrikaans")
        object Allemand : Name("allemand")
        object Albanais : Name("albanais")
        object Alsacien : Name("alsacien")
        object Amharique : Name("amharique")
        object Anglais : Name("anglais")
        object Arabe : Name("arabe")
        object Arameen : Name("araméen")
        object ArameenOccidental : Name("araméen occidental moderne ou néo-araméen occidental")
        object Armenien : Name("arménien")
        object Assamais : Name("assamais")
        object Azeri : Name("azéri")
        object Bachkir : Name("bachkir")
        object Basque : Name("basque")
        object Bengali : Name("bengali")
        object Berbere : Name("berbère")
        object Bichelamar : Name("bichelamar")
        object Bielorusse : Name("biélorusse")
        object Birman : Name("birman")
        object Bosniaque : Name("bosniaque")
        object Brahui : Name("brahui")
        object Breton : Name("breton")
        object Bulgare : Name("bulgare")
        object Cambodge : Name("cambodge")
        object Carelien : Name("carélien")
        object Catalan : Name("catalan")
        object Cherokee : Name("cherokee")
        object Ciluba : Name("ciluba")
        object Comorien : Name("comorien")
        object Coreen : Name("coréen")
        object Cornique : Name("cornique")
        object Creole : Name("créole")
        object Croate : Name("croate")
        object Dalmate : Name("dalmate")
        object Dari : Name("dari")
        object Danois : Name("danois")
        object Drehu : Name("drehu")
        object DzongKha : Name("rDzong-kha")
        object Ecossais : Name("écossais")
        object Edo : Name("edo")
        object Espagnol : Name("espagnol")
        object Esperanto : Name("espéranto")
        object Estonien : Name("estonien")
        object Finnois : Name("finnois")
        object Francais : Name("français")
        object Frioulan : Name("frioulan")
        object Frison : Name("frison")
        object Galicien : Name("galicien")
        object Gallo : Name("gallo")
        object Gallois : Name("gallois")
        object GEorgien : Name("géorgien")
        object Gotique : Name("gotique")
        object GrecAncien : Name("grec ancien")
        object GrecModerne : Name("grec moderne")
        object Guarani : Name("guarani")
        object Gujarati : Name("gujarati")
        object Haoussa : Name("haoussa")
        object Hebreu : Name("hébreu")
        object Hindi : Name("hindi")
        object Hittite : Name("hittite")
        object Hongrois : Name("hongrois")
        object Ilokano : Name("ilokano")
        object Indonesien : Name("indonésien")
        object Interlingua : Name("interlingua")
        object Inuit : Name("inuit")
        object Inuktitut : Name("inuktitut")
        object Irlandais : Name("irlandais")
        object Islandais : Name("islandais")
        object Italien : Name("italien")
        object Japonais : Name("japonais")
        object Javanais : Name("javanais")
        object Jersiais : Name("jersiais")
        object JudeoEspagnol : Name("judéo-espagnol")
        object Kannada : Name("kannada")
        object Kashmiri : Name("kashmiri")
        object Kazakh : Name("kazakh")
        object Khanty : Name("khanty")
        object Khmer : Name("khmer")
        object Kikai : Name("kikai")
        object Kim : Name("kim")
        object Kirghiz : Name("kirghiz")
        object Kunigami : Name("kunigami")
        object Kurde : Name("kurde")
        object Ladin : Name("ladin")
        object LangueDesSignes : Name("langue des signes")
        object Laotien : Name("laotien")
        object Lapon : Name("lapon")
        object Latin : Name("latin")
        object Letton : Name("letton")
        object Lingala : Name("lingala")
        object Lituanien : Name("lituanien")
        object Live : Name("live")
        object Luxembourgeois : Name("luxembourgeois")
        object Macedonien : Name("macédonien")
        object Malais : Name("malais")
        object Malayalam : Name("malayalam")
        object Malgache : Name("malgache")
        object Mande : Name("mandé")
        object Mannois : Name("mannois")
        object Mansi : Name("mansi")
        object Marathi : Name("marathi")
        object Mari : Name("mari")
        object Masana : Name("masana ou masa")
        object Maya : Name("maya")
        object Meitei : Name("meitei")
        object Miyako : Name("miyako")
        object Mongol : Name("mongol")
        object Nahuatl : Name("nahuatl")
        object Nauruan : Name("nauruan")
        object Neerlandais : Name("néerlandais")
        object Nepalais : Name("népalais")
        object Neware : Name("néware")
        object Nicois : Name("niçois")
        object Normand : Name("normand")
        object Norvegien : Name("norvégien")
        object Nushu : Name("nushu")
        object Occitan : Name("occitan")
        object Okinawais : Name("okinawais")
        object Oriya : Name("oriya")
        object Ossete : Name("ossète")
        object Ouigour : Name("ouïgour")
        object Ourdou : Name("ourdou")
        object Ouzbek : Name("ouzbek")
        object Pali : Name("pâli")
        object Pashto : Name("pashto")
        object Penjabi : Name("penjabi")
        object Persan : Name("persan")
        object Peul : Name("peul")
        object Picard : Name("picard")
        object Pijin : Name("pijin")
        object Polonais : Name("polonais")
        object Portugais : Name("portugais")
        object Prakrit : Name("prâkrit")
        object Provencal : Name("provençal")
        object Qiang : Name("qiang")
        object Quechua : Name("quechua")
        object Romanche : Name("romanche")
        object Roumain : Name("roumain")
        object Rromani : Name("rromani")
        object Russe : Name("russe")
        object Same : Name("same")
        object Sanskrit : Name("sanskrit")
        object Sarde : Name("sarde")
        object Scots : Name("scots")
        object Serbe : Name("serbe")
        object SerboCroate : Name("serbo-croate")
        object Sicilien : Name("sicilien")
        object Slovaque : Name("slovaque")
        object Slovene : Name("slovène")
        object Sorabe : Name("sorabe")
        object Suedois : Name("suédois")
        object Swahili : Name("swahili")
        object Sadjik : Name("tadjik")
        object Tagalog : Name("tagalog")
        object Tahitien : Name("tahitien")
        object Tamoul : Name("tamoul")
        object Tangoute : Name("tangoute")
        object TaraonDigaru : Name("Taraon-Digaru")
        object Tatar : Name("tatar")
        object Tcheque : Name("tchèque")
        object Tcheremisse : Name("tchérémisse")
        object Tchetchene : Name("tchétchène")
        object Tchiluba : Name("tchiluba")
        object Telougou : Name("télougou")
        object Thai : Name("thaï")
        object Tibetain : Name("tibétain")
        object Tigrinya : Name("tigrinya")
        object Tokharien : Name("tokharien")
        object TokPisin : Name("tok pisin")
        object Toungouse : Name("toungouse")
        object Toupouri : Name("toupouri")
        object Turc : Name("turc")
        object Turkmene : Name("turkmène")
        object Ukrainien : Name("ukrainien")
        object Vepse : Name("vepse")
        object Vietnamien : Name("vietnamien")
        object Volapuk : Name("volapük")
        object Vote : Name("vote")
        object Wallon : Name("wallon")
        object Wolof : Name("wolof")
        object Xhosa : Name("xhosa")
        object Viddish : Name("yiddish")
        object Vonaguni : Name("yonaguni")
        object Voruba : Name("yoruba")
        object Zoulou : Name("zoulou")
    }

}

val languagesList = listOf(
    "abenaki",
    "afrikaans",
    "allemand",
    "albanais",
    "alsacien",
    "amharique",
    "anglais",
    "arabe",
    "araméen",
    "araméen occidental moderne ou néo-araméen occidental",
    "arménien",
    "assamais",
    "azéri",
    "bachkir",
    "basque",
    "bengali",
    "berbère",
    "bichelamar",
    "biélorusse",
    "birman",
    "bosniaque",
    "brahui",
    "breton",
    "bulgare",
    "cambodge",
    "carélien",
    "catalan",
    "cherokee",
    "ciluba",
    "comorien",
    "coréen",
    "cornique",
    "créole",
    "croate",
    "dalmate",
    "dari",
    "danois",
    "drehu",
    "rDzong-kha",
    "écossais",
    "edo",
    "espagnol",
    "espéranto",
    "estonien",
    "finnois",
    "français",
    "frioulan",
    "frison",
    "galicien",
    "gallo",
    "gallois",
    "géorgien",
    "gotique",
    "grec ancien",
    "grec moderne",
    "guarani",
    "gujarati",
    "haoussa",
    "hébreu",
    "hindi",
    "hittite",
    "hongrois",
    "ilokano",
    "indonésien",
    "interlingua",
    "inuit",
    "inuktitut",
    "irlandais",
    "islandais",
    "italien",
    "japonais",
    "javanais",
    "jersiais",
    "judéo-espagnol",
    "kannada",
    "kashmiri",
    "kazakh",
    "khanty",
    "khmer",
    "kikai",
    "kim",
    "kirghiz",
    "kunigami",
    "kurde",
    "ladin",
    "langue des signes",
    "laotien",
    "lapon",
    "latin",
    "letton",
    "lingala",
    "lituanien",
    "live",
    "luxembourgeois",
    "macédonien",
    "malais",
    "malayalam",
    "malgache",
    "mandé",
    "mannois",
    "mansi",
    "marathi",
    "mari",
    "masana ou masa",
    "maya",
    "meitei",
    "miyako",
    "mongol",
    "nahuatl",
    "nauruan",
    "néerlandais",
    "népalais",
    "néware",
    "niçois",
    "normand",
    "norvégien",
    "nushu",
    "occitan",
    "okinawais",
    "oriya",
    "ossète",
    "ouïgour",
    "ourdou",
    "ouzbek",
    "pâli",
    "pashto",
    "penjabi",
    "persan",
    "peul",
    "picard",
    "pijin",
    "polonais",
    "portugais",
    "prâkrit",
    "provençal",
    "qiang",
    "quechua",
    "romanche",
    "roumain",
    "rromani",
    "russe",
    "same",
    "sanskrit",
    "sarde",
    "scots",
    "serbe",
    "serbo-croate",
    "sicilien",
    "slovaque",
    "slovène",
    "sorabe",
    "suédois",
    "swahili",
    "tadjik",
    "tagalog",
    "tahitien",
    "tamoul",
    "tangoute",
    "Taraon-Digaru",
    "tatar",
    "tchèque",
    "tchérémisse",
    "tchétchène",
    "tchiluba",
    "télougou",
    "thaï",
    "tibétain",
    "tigrinya",
    "tokharien",
    "tok pisin",
    "toungouse",
    "toupouri",
    "turc",
    "turkmène",
    "ukrainien",
    "vepse",
    "vietnamien",
    "volapük",
    "vote",
    "wallon",
    "wolof",
    "xhosa",
    "yiddish",
    "yonaguni",
    "yoruba",
    "zoulou",
)

