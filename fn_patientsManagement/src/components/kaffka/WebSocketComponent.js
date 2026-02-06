import React, { useState, useEffect } from 'react';

function WebSocketComponent() {
  const [message, setMessage] = useState('');
  const [messages, setMessages] = useState([]);
  const [ws, setWs] = useState(null);

  useEffect(() => {
    const socket = new WebSocket('ws://localhost:8080/ws');
    socket.onopen = () => console.log('Connected');
    socket.onmessage = (event) => {
      setMessages(prev => [...prev, event.data]);
    };
    setWs(socket);

    return () => socket.close();
  }, []);


  const send = () => {
    if (ws && message) {
      ws.send(message);
      setMessage('');
    }
  };

  return (
    <div>
      <input value={message} onChange={e => setMessage(e.target.value)} />
      <button onClick={send}>යවන්න</button>
      <ul>
        {messages.map((msg, i) => <li key={i}>{msg}</li>)}
      </ul>
    </div>
  );
}

export default WebSocketComponent;