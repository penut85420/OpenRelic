# OpenRelic  
  
這是一個針對Warframe的遺物系統開發的小程式  
這是第二版的程式，前身為WarframeVoidHelper  
原本只是Java程式設計的期末專案而已  
不過希望功能可以更完善且更彈性一點  
所以進行重建的工程  
  
因為官方的遺物系統實在有點複雜  
每次推出新P或者復刻都會很混亂  
希望可以透過較少的力氣，對社群有更大的貢獻  
大家只需要共同維護一筆Data  
希望可以做到即便我有一天也AFK了  
也不怕沒有人做遺物統整給大家  
  
***
## 下載區  
主程式 + 資料: [Release Alpha v1.1.4](https://goo.gl/DRXaLF)  
只下載資料: [Data U21.6.0](https://goo.gl/TAMNXk)  
  
### 如何開啟?  
需要先安裝 [Java JRE 8](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)  
解壓縮完後點兩下 `OpenRelic.bat` 即可開啟  
  
### 如何更新資料?  
在此之前，你可能會需要解壓縮的工具 [7-Zip](http://www.7-zip.org/)  
下載資料後將壓縮檔的內容覆蓋 `Data` 資料夾即可

遺物相關資料與圖示版權歸屬Digital Extremes  
如有侵權請告知  
  
***
2017/09/14
改動：
1. VoidRelic描述遺物代號的部分，紀元跟代號可以接受空格或者Tab分隔
2. 增加處理Wiki的Vaulted Relic資料，能順便比對現有資料的功能
  
資料更新：
1. 新增遺物資料
2. 更新入庫遺物資料
  
***
2017/08/02  
新增
+ 增加一支程式，專門處理從Wiki上複製下來的資料
+ 在選擇裝備的視窗增加搜尋功能

更新
+ 今日的遊戲更新，有四個新的遺物進入了虛空寶庫

修正
+ 資料錯誤：古紀S5已經進入虛空寶庫了  
+ 修正虛空倉管的核取方塊背景總是白色的問題
+ 修正虛空倉管沒有任何資料時無法再次打開程式的問題

***
2017/08/01  
新增  
+ 完成虛空倉管的開發
  
修正  
+ 修正改變語言時，遺物總覽的表格標題不會改變的問題
+ 暫時將Data縮減，只留目前需要的資料就好
   
***
2017/07/31  
新增  
+ 新增虛空倉管的頁面
+ 新增關於開發團隊的視窗
+ 完成裝備選擇的對話視窗
  
修正  
+ 修正部分用詞與翻譯的錯誤
+ 增加獨立Data的載點
+ 簡化修改字體或改變語言時的刷新程序
+ 修正變更語言時，遺物總覽的表格標題不會更動的問題
  
***
2017/06/16
+ 完成遺物檢視表格的排序功能
+ 初步完成搜尋功能
+ 可選擇啟用即時搜尋
+ 完成FORMA藍圖的過濾功能  
+ 完成入庫遺物的過濾功能  
+ 新增簡易的字型修改功能
+ 將遺物資料翻譯成英文
+ 幫視窗增加一個圖示 (取自Wiki)
  
***
2017/06/15  
+ 完成遺物資料統整
+ 完成遺物資料的IO
+ 建立初步的介面
+ 設計多語言系統
