import asyncio

async def call_async_function():
    print("This is an Asynchronous Function !!")

async def main():
    await call_async_function()

if __name__ == "__main__":
    asyncio.run(main())