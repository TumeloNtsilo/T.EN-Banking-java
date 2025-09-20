server_script := script/run_server.sh
client_script := script/run_client.sh

prepare :
	@echo preparing the scripts
	@chmod +x $(server_script)
	@chmod +x $(client_script)

server :
	@echo running the server
	@$(server_script)

client :
	@echo running the client
	@$(client_script)

