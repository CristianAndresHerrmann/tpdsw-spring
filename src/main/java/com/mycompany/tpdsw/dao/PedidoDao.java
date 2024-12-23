package com.mycompany.tpdsw.dao;

import java.util.List;

import org.hibernate.Session;

import com.mycompany.tpdsw.exception.ClienteNoEncontradoException;
import com.mycompany.tpdsw.exception.PedidoNoEncontradoException;
import com.mycompany.tpdsw.model.Estado;
import com.mycompany.tpdsw.model.Pedido;
import com.mycompany.tpdsw.service.HibernateUtil;

public class PedidoDao extends GenericDAO<Pedido, Integer> {

    public PedidoDao() {
        super(Pedido.class);
    }

    public List<Pedido> findByCliente(Integer idCliente) throws ClienteNoEncontradoException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Pedido p " +
                    "WHERE p.cliente.id = :idCliente " +
                    "AND p.cliente.activo = true";

            List<Pedido> pedidos = session.createQuery(hql, Pedido.class)
                    .setParameter("idCliente", idCliente)
                    .getResultList();

            if (pedidos.isEmpty()) {
                throw new ClienteNoEncontradoException("No se encontraron pedidos del cliente con ID:" + idCliente);
            }
            return pedidos;

        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar los pedidos para el cliente con ID: " + idCliente;
            throw new RuntimeException(errorMessage, e);
        }
    }

    public List<Pedido> findByEstado(Estado estado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Pedido p " +
                    "WHERE p.estado = :estado ";

            List<Pedido> pedidos = session.createQuery(hql, Pedido.class)
                    .setParameter("estado", estado)
                    .getResultList();

            if (pedidos.isEmpty()) {
                throw new PedidoNoEncontradoException("No se encontraron pedido con estado:" + estado);
            }
            return pedidos;

        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar los pedidos con el estado:" + estado;
            throw new RuntimeException(errorMessage, e);
        }
    }

    public List<Pedido> findByIdVendedor(Integer idVendedor) throws PedidoNoEncontradoException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Consulta HQL para obtener los pedidos asociados al vendedor con el ID dado
            String hql = "SELECT p FROM Pedido p " +
                    "JOIN p.pedidoItemPedidos pip " +
                    "JOIN pip.itemPedido ip " +
                    "JOIN ip.itemMenu im " +
                    "WHERE im.vendedor.activo = true " +
                    "AND im.vendedor.id = :idVendedor";

            // Ejecutar la consulta y devolver la lista de pedidos
            List<Pedido> pedidos = session.createQuery(hql, Pedido.class)
                    .setParameter("idVendedor", idVendedor)
                    .getResultList();

            return pedidos;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar los pedidos por vendedor", e);
        }
    }
}
