PostMan TEST API   
[mongoDB.postman_collection.json](https://github.com/user-attachments/files/17028402/mongoDB.postman_collection.json)  

forex API，從 DB取出日期區間內美元 /台幣的歷史資料 Post or Get  
http://localhost:8080/portfolio/api/forex/historical  
reuqestBody  
{  
    "startDate":"2024/10/01",  
    "endDate":"2024/11/06",  
    "currency":"usd"  
}  

排程 每日 18:00呼叫 API  
<img  width="100%" src="https://github.com/reve777/MongoDB/blob/main/Spring-MongoDB/src/main/resources/static/images/schedule.png" />
