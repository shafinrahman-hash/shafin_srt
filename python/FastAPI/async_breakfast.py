import time
import asyncio

async def make_coffee():
    print("This is the function to make coffee")
    await asyncio.sleep(5)

async def toast_bread():
    print("This is the function to toast bread ")
    await asyncio.sleep(5)

async def main():
    start_time = time.time()
    coffee = asyncio.create_task(make_coffee())
    bread = asyncio.create_task(toast_bread())

    result_coffee = await(coffee)
    result_bread = await(bread)
#    batch = asyncio.gather(make_coffee(),toast_bread())
#    result_coffee, result_toast_bread = await batch
    end_time = time.time()
    exec_time = end_time - start_time
    print("Total execution time :", exec_time)

if __name__ == "__main__":
    asyncio.run(main())