#! /bin/sh

APK_FILES="./app/build/outputs/apk/*/*/*-release.apk"
#DROPBOX_TOKEN="see ENV"

for FILE in $APK_FILES
do

  #check file is exist and not empty
  if [ -f  "${FILE}" ] && [ -s  "${FILE}" ]
  then

     echo "Found APK: ${FILE}"
     FILE_NAME=$(basename "${FILE}")

     curl -X POST https://content.dropboxapi.com/2/files/upload \
       --header "Authorization: Bearer ${DROPBOX_TOKEN}" \
       --header "Dropbox-API-Arg: {\"path\": \"\/${FILE_NAME}\",\"mode\": \"overwrite\",\"autorename\": true,\"mute\": false}" \
       --header "Content-Type: application/octet-stream" \
       --data-binary @"${FILE}"
  else
     echo "File ${FILE} is empty"; exit 1
  fi

done