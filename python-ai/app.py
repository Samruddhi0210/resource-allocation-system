from flask import Flask,request,jsonify
app=Flask(__name__)
@app.route('/')
def home():
    return"Python AI service is running!"
@app.route('/recommend',methods=['POST'])
def recommend():
    data=request.get_json()
    required_skill=data.get('requiredSkill')
    employees=data.get('employees')
    best_match=None
    for emp in employees:
        if emp.get('skill','').lower()==required_skill.lower():
            best_match=emp
            break
    if best_match:
        return jsonify({
            "recommended":best_match,
            "message":f"{best_match['name']} is recommended based on matching skill:{required_skill}"
        })  
    else:
        return jsonify({
            "recommended":None,
            "message":"No employee found with matching skill."
        })              
if __name__=='__main__':
    app.run(port=5000,debug=True)    