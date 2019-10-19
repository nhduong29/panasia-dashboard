import React, { Component } from 'react';
import { getCars, filterCars } from '../util/APIUtils';
import LoadingIndicator  from '../common/LoadingIndicator';
import { CAR_LIST_SIZE } from '../constants';
import { withRouter } from 'react-router-dom';
import './CarList.css';
import { Table, Tag , Form, Button, Select} from 'antd';
const columns = [
    {
      title: 'Brand',
      dataIndex: 'brand',
      key: 'brand'
    },
    {
      title: 'Year',
      dataIndex: 'year',
      key: 'year',
    },
    {
      title: 'Model',
      dataIndex: 'model',
      key: 'model',
    },
    {
      title: 'Color',
      key: 'color',
      dataIndex: 'color',
      render: color => (
        <span>
            <Tag color={color} key={color}>
            {color}
            </Tag>
        </span>
      ),
    }
  ];

const { Option } = Select;


class CarList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            brandFilter: '',
            colorFilter: '',
            cars: [],
            page: 0,
            pageSize: 10,
            totalElements: 0,
            totalPages: 0,
            last: true,
            isLoading: false
        };
        this.loadCarList = this.loadCarList.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.onBrandFilterChange = this.onBrandFilterChange.bind(this);
        this.onColorFilterChange = this.onColorFilterChange.bind(this);
    }

    loadCarList(brand='', color='', page = 0, size = CAR_LIST_SIZE) {
        let promise;
        if(brand == '' && color == ''){
            promise = getCars(page, size);
        }else{
            promise = filterCars(brand, color, page, size);
        }
        if(!promise) {
            return;
        }

        this.setState({
            isLoading: true
        });

        promise            
        .then(response => {
            this.setState({
                cars: response.content,
                page: response.page,
                pageSize: response.size,
                totalElements: response.totalElements,
                totalPages: response.totalPages,
                last: response.last,
                isLoading: false
            })
        }).catch(error => {
            this.setState({
                isLoading: false
            })
        });  
        
    }

    changePage = page => {
        this.loadCarList(this.state.brandFilter,this.state.colorFilter,page, CAR_LIST_SIZE);
    };

    onBrandFilterChange(value) {
        this.setState({
            brandFilter: value
        })
    }

    onColorFilterChange(value) {
        this.setState({
            colorFilter: value
        })
    }
      
    onFilterBlur() {
        console.log('blur');
    }
      
    onFilterFocus() {
        console.log('focus');
    }
      
    onFilterSearch(val) {
        console.log('search:', val);
    }


    handleSubmit(event) {
        event.preventDefault();
        this.loadCarList(this.state.brandFilter,this.state.colorFilter, 0, CAR_LIST_SIZE);
    }

    componentDidMount() {
        this.loadCarList();
    }

    componentDidUpdate(nextProps) {
        if(this.props.isAuthenticated !== nextProps.isAuthenticated) {
            // Reset State
            this.setState({
                cars: [],
                page: 0,
                pageSize: 5,
                totalElements: 0,
                totalPages: 0,
                last: true,
                isLoading: false
            });    
            this.loadCarList();
        }
    }

    render() {
        const {cars, pageSize, totalElements} = this.state;
        return (
            this.props.isAuthenticated && <div className="cars-container">
                <Form layout="inline" onSubmit={this.handleSubmit}>
                    <Form.Item>
                        <Select name="brand"
                            showSearch
                            placeholder="Filter with color"
                            optionFilterProp="children"
                            onChange={this.onBrandFilterChange}
                            onFocus={this.onFilterFocus}
                            onBlur={this.onFilterBlur}
                            onSearch={this.onFilterSearch}
                            filterOption={(input, option) => option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0 }>
                            <Option value="JAGUAR">JAGUAR</Option>
                            <Option value="HONDA">HONDA</Option>
                            <Option value="RENAULT">RENAULT</Option>
                            <Option value="BMW">BMW</Option>
                            <Option value="VOLVO">VOLVO</Option>
                            <Option value="AUDI">AUDI</Option>
                            <Option value="MERCEDES">MERCEDES</Option>
                        </Select>
                    </Form.Item>
                    <Form.Item>
                        <Select name="color" 
                            showSearch
                            placeholder="Filter with color"
                            optionFilterProp="children"
                            onChange={this.onColorFilterChange}
                            onFocus={this.onFilterFocus}
                            onBlur={this.onFilterBlur}
                            onSearch={this.onFilterSearch}
                            filterOption={(input, option) => option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0 }>
                            <Option value="Orange">Orange</Option>
                            <Option value="Aquamarine">Aquamarine</Option>
                            <Option value="Maroon">Maroon</Option>
                            <Option value="Red">Red</Option>
                            <Option value="Crimson">Crimson</Option>
                            <Option value="Goldenrod">Goldenrod</Option>
                            <Option value="Mauv">Mauv</Option>
                            <Option value="Indigo">Indigo</Option>
                            <Option value="Khaki">Khaki</Option>
                            <Option value="Fuscia">Fuscia</Option>
                            <Option value="Blue">Blue</Option>
                            <Option value="Teal">Teal</Option>
                            <Option value="Green">Green</Option>
                            <Option value="Purple">Purple</Option>
                            <Option value="Violet">Violet</Option>
                            <Option value="Puce">Puce</Option>
                            <Option value="Pink">Pink</Option>
                            <Option value="Yellow">Yellow</Option>
                            <Option value="Turquoise">Turquoise</Option>
                        </Select>
                    </Form.Item>
                    <Form.Item>
                    <Button type="primary" htmlType="submit">
                        Filter
                    </Button>
                    </Form.Item>
                </Form>
                <Table 
                    rowKey="id" 
                    columns={columns} 
                    dataSource={cars} 
                    pagination={
                        {
                            pageSize: pageSize, 
                            total : totalElements, 
                            showTotal: () => {
                                return(`Total ${totalElements} items`)
                            },
                            onChange : this.changePage
                        }
                    }>
                </Table>
                {
                    !this.state.isLoading && this.state.cars.length === 0 ? (
                        <div className="no-cars-found">
                            <span>No cars Found.</span>
                        </div>    
                    ): null
                }             
                {
                    this.state.isLoading ? 
                    <LoadingIndicator />: null                     
                }
            </div>
        );
    }
}

export default withRouter(CarList);