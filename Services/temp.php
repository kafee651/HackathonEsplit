curl -X POST -H "Authorization: Bearer ya29.GlvWBeAgapi-taaEUC5eQPyTXs2zJILDhMyup2MIK7L7JhKRToLzyyJxOTrPPLnA-PKXhmKqvhVrT_fANIkDcN6XijvEr0r7pagpcYvSWlR2C7ufOoAA-tFJJRP4" -H "Content-Type: application/json" -d '
{
"message":{
  "notification": {
    "title": "New Event is Created",
    "body": "replacevalue1",
  },
  "token": "fU_bjoN_JNk:APA91bGLGV7w1HR0M1T6QkTVJlxmCX8sl0ws8gzkr7ROBSwY4LfwcxFwS-0SSjt4-ReJ1SNtWTFl2U9kR5VI82pVH13glxXMfl4nyBjFTzRgG9DKNE7uOVofMOtZQtyNg5En9yYJHqCd"
  }
}' https://fcm.googleapis.com/v1/projects/hackathonesplit/messages:send