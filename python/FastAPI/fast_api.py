from fastapi import FastAPI, Query
from pydantic import BaseModel

app = FastAPI(
    title="Simple FastAPI Example",
    description="Basic FastAPI demo app",
    version="1.0.0"
)

class User(BaseModel):
    name: str
    age: int

users = []

@app.get("/")
def home():
    return {"message": "This is FastAPI"}

@app.get("/about")
def about():
    return {"app": "Simple FastAPI Example", "status": "running"}

@app.get("/health")
def health():
    return {"status": "ok"}

@app.get("/greet")
def greet(name: str = Query(..., min_length=2)):
    return {"message": f"Welcome, {name}"}

@app.post("/user")
def create_user(user: User):
    users.append(user.model_dump())
    return {
        "message": "User added successfully",
        "received_data": user.model_dump()
    }

@app.get("/users")
def get_users():
    return {"users": users}