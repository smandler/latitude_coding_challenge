import logo from './logo.png';
import './App.css';
import React from 'react';
import StockRequest from './features/StockRequest';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Latitude Code Challenge
        </p>
      </header>
      <div className='App'>
          <StockRequest />
        </div>      
    </div>
  );
}

export default App;

