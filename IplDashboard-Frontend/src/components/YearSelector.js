import {React} from 'react';
import { Link,useParams} from 'react-router-dom';
import './YearSelector.scss';

export const YearSelector= ({teamName})=>{

    let years=[];

    const {year}=useParams()

    const startyear= 2008;
    const endyear= 2021;

    for(let i=startyear;i<=endyear;i++){
        years.push(i);
    }

    return (
        // <h1>ss</h1>
       <ol className='YearSelector'>
        { years.map(Year => (
            <li key={Year}>
                <Link to={`/teams/${teamName}/matches/${Year}`}>
                {year==Year ? <span style={{color : '#4da375'}}>{year}</span> : Year }
                </Link>
            </li>
        )) }
       </ol>
    )
}