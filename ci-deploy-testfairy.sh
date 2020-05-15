#! /bin/sh

APK_FILES="./app/build/outputs/apk/*/*/*-release.apk"

# TESTFAIRY_API_KEY="see system ENV"

for FILE in $APK_FILES
do

   #check file is exist and not empty
   if [ -f "${FILE}" ] && [ -s "${FILE}" ]
     then
      echo "Found APK: ${FILE}"

      curl https://app.testfairy.com/api/upload \
        -F api_key="${TESTFAIRY_API_KEY}" \
        -F file=@"${FILE}" \
        -F format=readable \
        -F metrics='cpu,memory,network,network-requests,logcat' \
        -F max-duration="5h" \
        -F video-quality="medium" \
        -F video="on" \
        -F notify='off' \
        -F auto-update="off"
     else
       echo "File ${FILE} is empty"; exit 1
   fi

done
