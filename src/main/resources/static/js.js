var client = null;

function showMessage(value) {
    document.getElementById("response").textContent = value
}

function connect() {
    client = Stomp.client('ws://localhost:8081/websocket-service/chat');
    client.connect({}, function () {
        client.subscribe("/topic/music", function(message){

            showMessage(message.body)
        });
    })

    client.connect({}, function () {
        client.subscribe("/topic/weather", function(message){

            showMessage(message.body)
        });
    })
}
