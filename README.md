<body>

<h1>💼 AI-Powered Job Portal</h1>

<p>
The <strong>AI-Powered Job Portal</strong> is a hybrid microservices platform that:
</p>

<ul>
  <li>Orchestrates business logic using <strong>Spring Boot</strong></li>
  <li>Performs resume parsing & matching using <strong>FastAPI & Python</strong></li>
  <li>Uses <strong>Cosine Distance</strong> to calculate candidate compatibility</li>
  <li>Delivers real-time full-text search via <strong>Elasticsearch</strong></li>
</ul>

<hr>

<h2>📁 Project Structure</h2>

<pre>
JOB_PORTAL_PROJECT/
├── backend-core/         (Spring Boot - Auth & Orchestration)
│   ├── src/main/java
│   └── pom.xml
├── ai-service/           (FastAPI - NLP & Scoring)
│   ├── app.py
│   ├── vector_engine.py  (Cosine Distance Logic)
│   └── requirements.txt
└── .env                  (Configuration)
</pre>

<hr>

<h2>🧠 How It Works</h2>

<p>
The system performs:
</p>

<ol>
  <li>User authentication and profile management via Spring Boot</li>
  <li>Resume text extraction via FastAPI (Python)</li>
  <li>Vector embedding of Job Descriptions and Resumes</li>
  <li><strong>Cosine Distance</strong> calculation to rank best matches</li>
</ol>

<hr>

<h2>🔐 Environment Variables</h2>

<p>Create a <code>.env</code> file:</p>

<pre>
DB_URL=jdbc:postgresql://localhost:5432/jobportal
ELASTIC_URI=http://localhost:9200
REDIS_HOST=localhost
AI_SERVICE_URL=http://localhost:8000
</pre>

<hr>

<h2>🚀 Running the Server</h2>

<p><em>Ensure PostgreSQL, Redis, and Elasticsearch are installed and running locally.</em></p>

<p><strong>1. Start Databases</strong></p>
<pre>
# (Commands vary by OS, example for Linux/Mac)
sudo service postgresql start
redis-server
sudo systemctl start elasticsearch
</pre>

<p><strong>2. Core Backend (Spring Boot)</strong></p>
<pre>
cd backend-core
./mvnw spring-boot:run
</pre>

<p><strong>3. AI Service (Python)</strong></p>
<pre>
cd ai-service
pip install -r requirements.txt
uvicorn app:app --reload --port 8000
</pre>

<p>API available at:</p>

<ul>
  <li>Core: <code>http://127.0.0.1:8080/</code></li>
  <li>AI: <code>http://127.0.0.1:8000/docs</code></li>
</ul>

<hr>

<h2>📡 API Endpoint</h2>

<h3><code>POST /api/v1/jobs/match</code></h3>

<p>Calculates compatibility between a candidate and a job.</p>

<h3>Request Body</h3>

<pre>
{
  "candidate_id": "cand_12345",
  "job_id": "job_98765",
  "resume_text": "Senior Java Developer with 5 years experience...",
  "job_description": "Looking for Java expert with Microservices..."
}
</pre>

<h3>Example Response</h3>

<pre>
{
  "status": "success",
  "match_result": {
    "cosine_distance": 0.12,
    "similarity_score": 0.88,
    "verdict": "High Match"
  },
  "explanation": "Candidate vector is close to Job vector in embedding space."
}
</pre>

<hr>

<h2>📐 Optimization Model (Vector Space)</h2>

<p>The system converts text to vectors and calculates the distance:</p>

<pre>Distance(A, B) = 1 - CosineSimilarity(A, B)</pre>

<p>Where Cosine Similarity is:</p>

<pre>(A . B) / (||A|| * ||B||)</pre>

<p><strong>Interpretation:</strong></p>

<ul>
  <li><strong>Distance ≈ 0</strong>: Perfect match (Vectors are identical)</li>
  <li><strong>Distance ≈ 1</strong>: No match (Vectors are orthogonal)</li>
  <li><strong>Threshold</strong>: Matches with distance < 0.25 are recommended</li>
</ul>

<hr>

<h2>🔮 Future Enhancements</h2>

<ul>
  <li>Containerization (Docker support)</li>
  <li>Automated salary negotiation chatbot</li>
  <li>Video Interview Analysis with OpenCV</li>
  <li>Mobile App (React Native)</li>
</ul>

<hr>

<p>
Built using Spring Boot, FastAPI, and Scikit-Learn.
</p>

</body>
