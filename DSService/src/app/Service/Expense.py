from langchain_core.pydantic_v1 import BaseModel,Field
from typing import Optional


class Expense(BaseModel):
    amount: Optional[str] = Field(title="expense" , description="Expense made in the Transaction")
    merchant: Optional[str] =Field(title="merchant" , description="Merchant name who, the transaction has been done ")
    currency: Optional[str] =Field(title="currency" , description="currency of the transaction")

    