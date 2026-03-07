from flask import Flask, request, jsonify
from Service.messageService import messageService 


app = Flask(__name__)
app.config.from_pyfile('config.py')

messageService =messageService()

@app.route('/v1/ds/message/', methods=['POST'])
def handle_message():
    message = request.json.get('message')
    result = messageService.process_message(message)
    return jsonify(result)

@app.route('/', methods=['GET'])
def handle_get():
    print("Hello World")

if __name__ == "__main__":
    app.run(host="localhost" , port=8080 , debug=True)

