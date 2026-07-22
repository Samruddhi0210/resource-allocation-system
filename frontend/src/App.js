import React,{useState,useEffect} from "react";
function App(){
  const[employees,setEmployees]=useState([]);
  const[name,setName]=useState('');
  const[email,setEmail]=useState('');
  const[skill,setSkill]=useState('');
  const[skillNeeded,setSkillNeeded]=useState('');
  const[recommendation,setRecommendation]=useState(null);
  const containerStyle={
    maxWidth:'600px',
    margin:'40px auto',
    padding:'20px',
    frontFamily:'Arial,sans-serif',
    backgroundColor:'#f9f9f9',
    borderRadius:'8px',
    boxShadow:'0 2px 8x rgba(0,0,0,0.1)'
  };
  const inputStyle={
    padding:'8px',
    margin:'5px',
    borderRadius:'4px',
    border:'1px solid #ccc'
  };
  const buttonStyle={
    padding:'8px 16px',
    margin:'5px',
    backgroundColor:'#4CAF50',
    color:'white',
    border:'none',
    borderRadius:'4px',
    cursor:'pointer'
  }
  const handleRecommend=()=>{
    fetch(`http://localhost:8080/api/ai/recommend?skill=${skillNeeded}`)
      .then(res=>res.json())
      .then(data=>setRecommendation(data));
  };
  const fetchEmployees=()=>{
    fetch('http://localhost:8080/api/employees')
       .then(res=>res.json())
       .then(data=>setEmployees(data));

  };
  useEffect(()=>{
    fetchEmployees();
  },[]);
  const handleSubmit= (e)=> {
    e.preventDefault();
    
    const newEmployee={name,email,skill};
    fetch('http://localhost:8080/api/employees',{
      method:'POST',
      headers:{'Content-Type':'application/json'},
      body:JSON.stringify(newEmployee)
    })
      .then(res=>res.json())
      .then(()=>{
        setName('');
        setEmail('');
        setSkill('');
        fetchEmployees();
      });
  };
  return(
    <div style={containerStyle}>
      <h1>Employee Resource Allocation</h1>
      <form onSubmit={handleSubmit} style={{marginBottom:'20px'}}>
        <input
          style={inputStyle}
          type="text"
          placeholder="Name"
          value={name}
          onChange={(e)=>setName(e.target.value)}
          required
        />  
        <input
          style={inputStyle}
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e)=>setEmail(e.target.value)}
          required
        />
        <input
          style={inputStyle}
          type="text"
          placeholder="Skill"
          value={skill}
          onChange={(e)=>setSkill(e.target.value)}
          required
        />
        <button style={buttonStyle} type="submit">Add Employee</button>
          
      </form>
      <div style={{marginBottom:'20px'}}>
        <input
          style={inputStyle}
          type="text"
          placeholder="Skill needed for project"
          value={skillNeeded}
          onChange={(e)=>setSkillNeeded(e.target.value)}
          />
          <button style={buttonStyle} onClick={handleRecommend}>Get AI Recommendation</button>
          {recommendation &&(
            <p>{recommendation.message}</p>
          )}
      </div>
      <u1>
        {employees.map(emp=>(
          <li key={emp.id}>{emp.name}-{emp.skill}</li>
        ))}
      </u1>

    </div>

  );
}
export default App;