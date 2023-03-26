import React, { useState } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [amount, setAmount] = useState(100);
  const [fromCurrency, setFromCurrency] = useState('USD');
  const [toCurrency, setToCurrency] = useState('EUR');
  const [results, setResults] = useState([]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.get('http://localhost:8080/currency-conversion', {
        params: { amount, fromCurrency, toCurrency },
      });
      setResults(response.data);
    } catch (error) {
      console.error('Error fetching conversion data:', error);
    }
  };

  return (
    <div className="App">
      <h1>Currency Conversion</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Amount:
          <input type="number" value={amount} onChange={(e) => setAmount(e.target.value)} />
        </label>
        <label>
          From:
          <input type="text" value={fromCurrency} onChange={(e) => setFromCurrency(e.target.value)} />
        </label>
        <label>
          To:
          <input type="text" value={toCurrency} onChange={(e) => setToCurrency(e.target.value)} />
        </label>
        <button type="submit">Convert</button>
      </form>
      {results.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>Intermediary Currency</th>
              <th>From -> Intermediary</th>
              <th>Intermediary -> To</th>
              <th>Final Amount</th>
            </tr>
          </thead>
          <tbody>
            {results.map((result, index) => (
              <tr key={index}>
                <td>{result.intermediaryCurrency}</td>
                <td>{result.fromCurrencyToIntermediaryRate.toFixed(4)}</td>
                <td>{result.intermediaryToToCurrencyRate.toFixed(4)}</td>
                <td>{result.finalAmount.toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default App;
