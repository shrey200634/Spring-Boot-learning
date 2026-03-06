
from app.util.messageUtil import MessagesUtil
from Service.LLMService import LLMService


class MessageService:
    def __init__(self):
        self.messageUtil =MessagesUtil();
        self.llmService = LLMService();
