PostMan TEST API   
[mongoDB.postman_collection.json](https://github.com/user-attachments/files/17028402/mongoDB.postman_collection.json)  


__1.排程 每日 18:00呼叫 API__  
path:/Spring-MongoDB/src/test/java/com/portfolio/forexTest/ScheduleDataTest.java  
<img  width="100%" src="https://github.com/reve777/Spring_MongoDB/blob/main/Spring-MongoDB/src/main/resources/static/images/schedule.png" />


__2.forex API，從 DB取出日期區間內美元 /台幣的歷史資料 Post or Get__  
reuqestBody.currency.reuqestBody參數:  
 "usd" //需求request  
 "usdNtdRate"  

 "rmbNtd"  
 "rmbNtdRate"  

 "eurUsd"  
 "eurUsdRate"  

 "usdJpy"  
 "usdJpyRate"  

 "gbpUsd"  
 "gbpUsdRate"  

 "audUsd"  
 "audUsdRate"  

 "usdHkd"  
 "usdHkdRate"  

 "usdRmb"  
 "usdRmbRate"  

 "usdZar"  
 "usdZarRate"  

 "nzdUsd"  
 "nzdUsdRate"

---------------------------------------------------------------------------------------------------------------------
__http://localhost:8080/portfolio/api/forex/historical__  
reuqestBody  
{  
    "startDate":"2024/10/01",  
    "endDate":"2024/11/06",  
    "currency":"usd"  
}  
Response:  
Success:  
{
    "error": {
        "code": "0000",
        "message": "Success"
    },
    "currency": [
        {
            "date": "20241001",
            "usd": 0.63075
        },
        ...
        ]
}


---------------------------------------------------------------------------------------------------------------------
__http://localhost:8080/portfolio/api/forex/historical__  
reuqestBody  
{  
    "startDate":"2024/10/01",  
    "endDate":"2025/11/06",  
    "currency":"usd"  
}  
Response:  
Failed  
{
    "code": "E001",
    "message": "日期區間不符"
}
