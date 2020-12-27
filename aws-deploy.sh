# remove hardcoded role - replace with function to pull a role with policy lambda-full-access applied
	aws lambda update-function-code --function-name eligRules --zip-file fileb://`pwd`/target/lambda.jar

	# check if errored

	if [[ "$?" -ne 0 ]]
	then

	aws lambda create-function --function-name eligRules --runtime java8 --role arn:aws:iam::259437231177:role/lambda-full-access --handler com.r1.eligRules.Handler::handleRequest --zip-file fileb://`pwd`/target/lambda.jar --timeout 15

	fi
