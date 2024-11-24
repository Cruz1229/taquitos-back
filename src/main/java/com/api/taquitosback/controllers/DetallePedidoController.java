    package com.api.taquitosback.controllers;

    import com.api.taquitosback.dto.DetallePedidoDTO;
    import com.api.taquitosback.enums.TipoExtra;
    import com.api.taquitosback.models.DetallePedido;
    import com.api.taquitosback.models.Pedido;
    import com.api.taquitosback.services.DetallePedidoService;
    import com.api.taquitosback.services.PedidoService;
    import com.api.taquitosback.services.ProductoService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.*;

    @RestController
    @RequestMapping("/detallePedido")
    @CrossOrigin(origins = "*")
    public class DetallePedidoController {

        @Autowired
        private DetallePedidoService detallePedidoService;

        @Autowired
        private ProductoService productoService;

        @Autowired
        private PedidoService pedidoService;

        @GetMapping
        public ArrayList<DetallePedido> getDetallePedidos() {
            return this.detallePedidoService.getDetallePedidos();
        }

        @PostMapping
        public ResponseEntity<?> saveDetallePedido(@RequestBody List<DetallePedidoDTO> dtos) {
            Pedido pedido = new Pedido();
            pedido.setTotal(0);
            Pedido savedPedido = pedidoService.savePedido(pedido);

            List<DetallePedido> detalles = new ArrayList<>();

            for (DetallePedidoDTO dto : dtos) {
                System.out.println("Extras: " + dto.getExtras());
                dto.setPedidoId(savedPedido.getId());
                System.out.println("id: " + savedPedido.getId());
                detalles.add(detallePedidoService.saveDetallePedido(dto));
            }

            for (DetallePedido dt : detalles) {
                System.out.println(dt.getSubtotal());
            }

            int total = detalles.stream()
                    .mapToInt(DetallePedido::getSubtotal)
                    .sum();

            savedPedido.setTotal(total);
            pedidoService.updateById(savedPedido, savedPedido.getId());

            return ResponseEntity.ok(Map.of(
                    "pedidoId", savedPedido.getId(),
                    "detalles", detalles,
                    "total", total
            ));

//        public String saveDetallePedido(@RequestBody int IDs[]) {

//            Map<Integer, Integer> contador = new HashMap<>();
//
//            Pedido pedido = new Pedido(0L, 0);
//            Pedido pedidoOpt = pedidoService.savePedido(pedido);
//            System.out.println("idPedido: " + pedidoOpt.getId());
//
//            for (int id : IDs) {
//                contador.put(id, contador.getOrDefault(id, 0) + 1);
//            }
//
//            for (Map.Entry<Integer, Integer> entry : contador.entrySet()) {
//                int idProducto = entry.getKey();
//                int cantidad = entry.getValue();
//
//                Optional<Producto> productoOpt = productoService.getById((long) idProducto);
//
//                Producto producto = productoOpt.get();
//                int subTotal = producto.getPrecio() * cantidad;
//
//                DetallePedido dPedido = new DetallePedido(0L, cantidad, subTotal, idProducto, pedidoOpt.getId());
//
//                this.detallePedidoService.saveDetallePedido(dPedido);
//            }
//
//            List<DetallePedido> detallesPedido = this.detallePedidoService.getDetallesPedidoByPedidoId(pedidoOpt.getId());
//            int totalPedido = detallesPedido.stream().mapToInt(DetallePedido::getSubtotal).sum();
//
//            pedidoOpt.setTotal(totalPedido);
//            pedidoService.updateById(pedidoOpt, pedidoOpt.getId());
//
//            return "OK";
        }

        @GetMapping("/{id}/extras")
        public Set<TipoExtra> getExtrasDeDetallePedido(@PathVariable Long id) {
            Optional<DetallePedido> detallePedido = detallePedidoService.getById(id);
            if (detallePedido.isPresent()) {
                return detallePedidoService.getExtras(detallePedido.get());
            }
            return Collections.emptySet();
        }

        @GetMapping(path = "{id}")
        public Optional<DetallePedido> getDetallePedidoById(@PathVariable Long id) {
            return this.detallePedidoService.getById(id);
        }

        @PutMapping("/{id}")
        public ResponseEntity<DetallePedido> updateDetallePedidoById(@RequestBody DetallePedido request, @PathVariable("id") Long id) {
            try {
                DetallePedido updateDetallePedido = this.detallePedidoService.updateById(request, id);
                return ResponseEntity.ok(updateDetallePedido);
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping(path = "/{id}")
        public String deleteById(@PathVariable("id") Long id) {
            boolean ok = this.detallePedidoService.deleteDetallePedido(id);

            if (ok)
                return "DetallePedido eliminado";
            else
                return "Error al eliminar el detallePedidop";

        }
    }
