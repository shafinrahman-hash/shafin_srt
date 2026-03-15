from fastapi import FastAPI
from pydantic import BaseModel

class Person(BaseModel):
    name : str
    age  : int

person = []

application = FastAPI()

@application.get("/")
def get_value():
    return {"Name" : "Enter Name", "Age" : "Enter Age"}

@application.post("/post")
def post_value(candidate : Person):
    person.append(candidate.model_dump())