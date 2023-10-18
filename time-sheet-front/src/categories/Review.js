import Accordion from 'react-bootstrap/Accordion';
import useFetch from '../useFetch';
import Edit from './Edit';

const Review = () => {

    const [data, error] = useFetch("http://localhost:8080/api/category");

    const handleError = () => {
        
    }

    return (
        <div className='span-top'>
            <div className='row'>
                <div className='col-1'></div>
                <div className='col-10'>
                    <Accordion>
                        {data && data.map((category) => (
                            <Accordion.Item key={category.id} eventKey={category.id}>
                                <Accordion.Header>{category.name}</Accordion.Header>
                                <Accordion.Body>
                                    <Edit category={category}></Edit>
                                </Accordion.Body>
                            </Accordion.Item>
                        ))}
                    </Accordion>
                </div>
                <div className='col-1'></div>
            </div>
        </div>
    );
}

export default Review;