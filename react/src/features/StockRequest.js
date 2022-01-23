import React from 'react';
import axios from 'axios';
import moment from "moment";

export default class StockRequest extends React.Component {
  request = {
    id: '',
    startDate: Date.now(),
    endDate: Date.now(),
    stockPrices: ''
  }

  response = {
    processedDate: undefined,
    buyValue: 0,
    sellValue: 0,
    maxProfit: 0
  }

  constructor(props) {
    super(props);
    this.state = { response: {}, errorMessage: '', invalidStartDate: '', invalidEndDate: '', invalidPricesArray: '' };
  }

  handleChangeId = event => {
    this.setState({ 
      id: event.target.value
    });
  }
  
  handleChangeStartDate = event => {
    this.setState({ 
      invalidStartDate: ''
    });

    var val = moment(event.target.value, "DD-MM-YYYY hh:mm:ss", true)

    if ( val.isValid() ){
      this.setState({ 
        startDate: moment(event.target.value).format('YYYY-MM-DDThh:mm:ss')
      });
    }
    else {
      this.setState({ 
        invalidStartDate: 'Invalid Date'
      });
    }
  }

  handleChangeEndDate = event => {
    this.setState({ 
      invalidEndDate: ''
    });

    var val = moment(event.target.value, "DD-MM-YYYY hh:mm:ss", true)

    if ( val.isValid() ){
      this.setState({ 
        endDate: moment(event.target.value).format('YYYY-MM-DDThh:mm:ss')
      });
    }
    else {
      this.setState({ 
        invalidEndDate: 'Invalid Date'
      });
    }    
  }

  handleChangeStockPrices = event => {
    this.setState({ 
      invalidPricesArray: ''
    });

    const start = moment(this.state.startDate, 'DD-MM-YYYY hh:mm:ss A');
    const end = moment(this.state.endDate, 'DD-MM-YYYY hh:mm:ss A');
    
    const minutes = Math.abs(moment.duration(start.diff(end)).asMinutes());
    const prices = event.target.value.split(',');
    if (prices.length != minutes) {
      this.setState({ 
        invalidPricesArray: 'Please enter ' + minutes + ' comma separated values'
      });
    } else {
      this.setState({ 
        stockPrices: event.target.value.split(',')
      });
    }
  }

  handleSubmit = event => {
    event.preventDefault();

    const request = {
        id: this.state.id,
        startDate: this.state.startDate,
        endDate: this.state.endDate,
        stockPrices: this.state.stockPrices
      };
  
      let body =  JSON.stringify(request);  
  
      axios({
        method: 'post',
        url: 'http://localhost:8080/getMaxProfit',
        data: body,
        headers: {'Content-Type': 'application/json' }
        })
        .then(res => {
          console.log(res.data);
  
          const response = res.data;
          this.setState({ response });
        })
        .catch(error => {
          console.log(error.response)
          const errorMessage = error.response.data.message ? error.response.data.message : error.response.config.data;
          this.setState({ errorMessage });
        });
  }

  createResponseUI(){
    return  (
      <div>
        <p>Processed Date: {this.state.response.processedDate ? moment(this.state.response.processedDate).format('DD-MM-YYYY hh:mm:ss') : undefined}</p>
        <p>Buy Value: {this.state.response.buyValue}</p>
        <p>Sell Value: {this.state.response.sellValue}</p>
        <p className='form-control-profit'>MAX PROFIT: {this.state.response.maxProfit}</p>
        {this.state.errorMessage && (
          <p className="error"> {this.state.errorMessage} </p>
        )}
      </div>
    )       
 
 }

  render() {
    return (
      <div className='StockRequestClass'>
        <table>
        <tbody>
          <tr>
          <td>
            <h1>Stock Request</h1>
            <form onSubmit={this.handleSubmit}>
            <fieldset>
              <div>
                <label>
                  Identificator:
                  
                </label>
                <input className="form-control" type="text" refs="id" name="id" onChange={this.handleChangeId} required />
              </div>
              <div>
                <label>
                  Start Date: <p className="errorField"> {this.state.invalidStartDate} </p>
                  <input className="form-control" type="text" name="startDate" placeholder='DD-MM-YYYY hh:mm:ss' onChange={this.handleChangeStartDate} required />
                </label>
                </div>
              <div>
                <label>
                  End Date: <p className="errorField"> {this.state.invalidEndDate} </p>
                  <input className="form-control" type="text" name="endDate" placeholder='DD-MM-YYYY hh:mm:ss' onChange={this.handleChangeEndDate} required />
                </label>
              </div>
              <div className="field">
                <label>
                  Stock Prices:
                  <input className="form-control" type="text" name="stockPrices" placeholder='Type comma separated values' onChange={this.handleChangeStockPrices} required />
                </label>
                <p className="errorField"> {this.state.invalidPricesArray} </p>
              </div>
              <button type="submit">Submit</button>
              </fieldset>
            </form>
          </td>
          <td></td>
          <td>
          <h1>Stock Response</h1>
            {this.createResponseUI()}
          </td>
          </tr>
          </tbody>
        </table>
      </div>
    )
  }
}