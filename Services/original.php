curl -X POST -H "Authorization: Bearer ya29.GlvWBfWVtqan7FHqvSn-LtP4IAuVlmrxz19rpF-Sl025MjKEh5xGXcfr8DO5mJg7VSmuwxy8PQgFcNgQHb76Gv_lvQifOrNQ6iu9I4TgtpGtgTl2BRaza0FwpbIP" -H "Content-Type: application/json" -d '
{
"message":{
  "notification": {
    "title": "New Event is Created",
    "body": "replacevalue1",
  },
  "token": "replacevalue2"
  }
}' https://fcm.googleapis.com/v1/projects/hackathonesplit/messages:send