import React, { useState } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [amount, setAmount] = useState('');
  const [fromCurrency, setFromCurrency] = useState('');
  const [toCurrency, setToCurrency] = useState('');
  const [conversionResult, setConversionResult] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.get('http://localhost:8080/convert', {
        params: {
          amount,
          fromCurrency,
          toCurrency,
        },
      });

      console.log('Response data:', response.data);
      setConversionResult(response.data);
    } catch (error) {
      console.error('Error fetching conversion data:', error);
    }
  };



  return (
    <div className="App">
      <h1>Currency Converter</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          placeholder="Amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
        <input
          type="text"
          placeholder="From Currency"
          maxLength="3"
          value={fromCurrency}
          onChange={(e) => setFromCurrency(e.target.value.toUpperCase())}
        />
        <input
          type="text"
          placeholder="To Currency"
          maxLength="3"
          value={toCurrency}
          onChange={(e) => setToCurrency(e.target.value.toUpperCase())}
        />
        <button type="submit">Convert</button>
      </form>
      {conversionResult?.mostProfitableForClient && conversionResult?.mostProfitableForService && (
        <div>
          <h2>Conversion Results</h2>
          <p>
            Most Profitable for Client: {conversionResult.mostProfitableForClient.intermediaryCurrency} -{' '}
            {conversionResult.mostProfitableForClient.convertedAmount}
          </p>
          <p>
            Most Profitable for Service: {conversionResult.mostProfitableForService.intermediaryCurrency} -{' '}
            {conversionResult.mostProfitableForService.convertedAmount}
          </p>
          <table>
            <thead>
              <tr>
                <th>Intermediary Currency</th>
                <th>Converted Amount</th>
                <th>Profit</th>
              </tr>
            </thead>
            <tbody>
              {conversionResult.conversions.map((conversion, index) => (
                <tr key={index}>
                  <td>{conversion.intermediaryCurrency}</td>
                  <td>{conversion.convertedAmount}</td>
                  <td>{conversion.profit}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default App;



