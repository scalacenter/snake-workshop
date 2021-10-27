import React, { useEffect } from 'react';
import Layout from '@theme/Layout';

function Game() {
  const canvasRef = React.useRef(null);
  React.useEffect(() =>
    snakeMain(canvasRef.current)
  )
  return (
    <Layout title="Game">
      <canvas
        ref={canvasRef}
        height="600"
        width="600"
        style={{"backgroundColor": "#3e3e3e"}}>
       </canvas>
    </Layout>
  );
}

export default Game;