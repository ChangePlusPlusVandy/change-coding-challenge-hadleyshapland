import React, { useState } from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  //get elon Tweets
  const [elonTweets, setElonTweets] = useState(null);
  const proxyurl = "https://cors-anywhere.herokuapp.com/";
  const elonApiUrl = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=elonmusk?count=10";

  const fetchElonData = async() => {
    return fetch(proxyurl + elonApiUrl, {
      headers:{
        'Access-Control-Allow-Origin':'*'
      },
    })
    .then((response) => response.json())
    .then((json) => {
      return json.text;
    })
    .catch((error) => {
      console.error(error);
    });

  }



  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Hello, world!
        </p>

        <div>
        <button className="fetch-button" onClick={fetchElonData}>
        Fetch Elon data</button>
        </div>

        //display elon Tweets
        <div className = "displayTweets">

        {elonTweets &&
          elonTweets.map((tweet, index) => {
            const text = tweet.text;

            return (
              <div className="tweet" key={index}>
                <h2>{tweet.text}</h2>
              </div>
            );
          })}

        </div>


      </header>
    </div>
  );
}

export default App;
