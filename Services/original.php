curl -X POST -H "Authorization: Bearer ya29.GlvXBYGFm1PXF2QYTsF3wCCZ669dkDjpaMtD8o1sMGa_kgtyAS5iFEwk4s2Z6IUlkLDMnLVP3N-qugHa0R69P-RW-1PDbt374btSZYObbI9dlM-EdpwfcMMYwGwJ" -H "Content-Type: application/json" -d '
{
"message":{
  "notification": {
    "title": "New Group is Created",
    "body": "replacevalue1",
  },
  "token": "replacevalue2"
  }
}' https://fcm.googleapis.com/v1/projects/hackathonesplit/messages:send