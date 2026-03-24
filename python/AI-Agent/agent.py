from strands import Agent, tool
from strands_tools import calculator
from strands.models.ollama import OllamaModel

@tool
def word_count(text: str) -> int:
    return len(text.split())


def main():
    model = OllamaModel(
        host="http://localhost:11434",
        model_id="qwen3"
    )
    agent = Agent(model=model, tools=[calculator, word_count])
    response = agent(
        "Use the tools if needed, then give me a final plain-English answer only. What is 15 * 24? And how many words are in 'Hello world from Strands'?"
    )
    print(response)


if __name__ == "__main__":
    main()
