const Express = require('express');
const router = new Express.Router();

const delay = (ms) => new Promise((res) => setTimeout(res, ms));

router.get('/health', (req, res) => {
  res.status(200);
  res.json({ status: 'up' });
});

router.get('/jugadores', async (req, res) => {
  await delay(2000);
  res.status(200);
  //res.status(409).send(new Error());
  res.json(require('../mock/jugadores-list.json'));
});

router.get('/partidos', async (req, res) => {
  await delay(2000);
  res.status(200);
  //res.status(409).send(new Error());
  res.json(require('../mock/partidos-list.json'));
});

router.post('/jugadores', async (req, res) => {
  await delay(1000);
  //res.status(409).send(new Error());
  res.status(201);
  res.json({ message: 'usuario cargado' });
});
router.post('/partidos', async (req, res) => {
  await delay(1000);
  //res.status(409).send(new Error());
  res.status(201);
  res.json({ message: 'partido cargado' });
});

router.put('/jugadores/:id', async (req, res) => {
  await delay(3000);
  res.status(200);
  res.json({ message: 'usuario modificado' });
});

router.put('/partidos/:id', async (req, res) => {
  await delay(3000);
  res.status(200);
  res.json({ message: 'partido modificado' });
});

router.put('/partidos/sumarPunto/:id', async (req, res) => {
  await delay(3000);
  res.status(200);
  res.json({ message: 'Se sumo el punto' });
});

router.delete('/jugadores/:id', async (req, res) => {
  await delay(3000);
  res.status(200);
  res.json({message: 'usuario eliminado'});
});

router.delete('/partidos/:id', async (req, res) => {
  await delay(3000);
  res.status(200);
  res.json({message: 'partido eliminado'});
});


module.exports = router;
