- URL：https://{host}/api/v1


| method  | route  | feature |parameter|
| ------- | ------ | ------- | ------- |
| GET| **/pl-goals** |get all PLs||
| GET| **/pl-goals/{pl-id}/initialtives** |get initialtives in one PL||
| GET| /pl-goal/progarm                    | ||
| GET| **/initialtives** |get all initialtives||
| GET | **/initialtives/{initialtive id}/epics** |get epics in one initialtive||
| GET| /initialtives/progarm| ||
| GET| **/epics** |get epics in some programs.  Default is all programs|"programs",  not required, List<String> |
| GET| /epics/{epic id}/team | TBD ||
| GET| /epic/progarm |  ||
| GET| /team/progarm | TBD ||
| GET| **/programs** |get all programs||



