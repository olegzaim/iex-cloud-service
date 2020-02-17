# iex-cloud-service 
      It's a challenge that i using the IEX Developer Platform to work with stock price data.
      
      The solution implemented:
        * poll the IEX Developer Platform API at a configurable interval for the prices of a configurable set of companies ("symbols"), their names and logos (URL to the logo image);
        
        * store a history of all retrieved prices in MySQL
          HTTP Endpoint for retrived price history is:
          iex/company/history
          
        * return a JSON document that includes each queried company's name, logo (URL) and the list of polled
          prices, optionally limited by the given time frame.
          HTTP Endpoint that returns prices by company symbol is: 
          iex/company?symbol=aapl&symbol=ibm&time=1m 
          where time it's not required and by default is a dynamic(One day	Will return 1d or 1m data depending on the day or week and time of day.
            // Intraday per minute data is only returned during market hours)
          Time parameters:
          max	All available data up to 15 years	Historically adjusted market-wide data
          5y	Five years	Historically adjusted market-wide data
          2y	Two years	Historically adjusted market-wide data
          1y	One year	Historically adjusted market-wide data
          ytd	Year-to-date	Historically adjusted market-wide data
          6m	Six months	Historically adjusted market-wide data
          3m	Three months	Historically adjusted market-wide data
          1m	One month (default)	Historically adjusted market-wide data
          1mm	One month	Historically adjusted market-wide data in 30 minute intervals
          5d	Five Days	Historically adjusted market-wide data by day.
          5dm	Five Days	Historically adjusted market-wide data in 10 minute intervals
          
          All available symbols here https://iextrading.com/trading/eligible-symbols/ 
      Missing to make the project production-ready:
        *Adding more validations;
        *Adding authorization and authentication;
        *Adding DB migration tools like "Flyway";
        *Deploying service and DB on the server machine;
        *Optionaly adding Front-end;
