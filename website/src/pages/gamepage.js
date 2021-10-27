import React from 'react';
import Layout from '@theme/Layout';

function Game() {
  return (
    <Layout title="Game">
      <canvas
        id="canvas"
        height="300"
        width="300"
        style={{"backgroundColor": "#3e3e3e"}}>
       </canvas>
       <ScriptTag type="text/javascript" src="static/js/game.js"/>
    </Layout>
  );
}

export default Game;