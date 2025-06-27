import React, { useEffect, useState } from "react";
import axios from "axios";

const API_BASE = "http://localhost:8080/api";

function App() {
	const [products, setProducts] = useState([]);
	const [cart, setCart] = useState(null);
	const [cartId, setCartId] = useState(null);

	// Load mock products
	useEffect(() => {
		axios.get(`${API_BASE}/products`).then((res) => setProducts(res.data));
	}, []);

	useEffect(() => {
		const existingCartId = localStorage.getItem("cartId");

		if (existingCartId) {
			setCartId(existingCartId);

			// Load the cart from backend
			axios
				.get(`${API_BASE}/cart/${existingCartId}`)
				.then((res) => setCart(res.data))
				.catch((err) => {
					console.error("Cart not found, creating a new one...");
					createNewCart();
				});
		} else {
			createNewCart();
		}
	}, []);

	const createNewCart = () => {
		axios.post(`${API_BASE}/cart/create`).then((res) => {
			setCart(res.data);
			setCartId(res.data.id);
			localStorage.setItem("cartId", res.data.id); // Save to localStorage
		});
	};

	// Fetch cart contents
	const refreshCart = () => {
		if (!cartId) return;
		axios.get(`${API_BASE}/cart/${cartId}`).then((res) => setCart(res.data));
	};

	const addToCart = (product) => {
		axios
			.post(`${API_BASE}/cart/add`, {
				title: product.title,
				description: product.description,
				price: product.price,
				imageUrl: product.imageUrl,
				quantity: 1,
				cartId: cartId,
			})
			.then(() => refreshCart());
	};

	const removeFromCart = (itemId) => {
		axios
			.delete(`${API_BASE}/cart/${cartId}/item/${itemId}`)
			.then(() => refreshCart());
	};

	const updateQuantity = (itemId, quantity) => {
		axios
			.put(`${API_BASE}/cart/${cartId}/item/${itemId}?quantity=${quantity}`)
			.then(() => refreshCart());
	};

	const clearCart = () => {
		axios.delete(`${API_BASE}/cart/clear-all`).then(() => {
			localStorage.removeItem("cartId");
			createNewCart();
		});
	};

	const checkout = () => {
		axios
			.get(`${API_BASE}/cart/${cartId}/checkout`)
			.then((res) => {
				window.location.href = res.data.url;
			})
			.catch(() => alert("Checkout failed"));
	};

	return (
		<div className="p-4">
			<h1 className="text-3xl font-bold mb-4">QuickShop</h1>

			<h2 className="text-xl font-semibold">Products</h2>
			<div className="grid grid-cols-2 md:grid-cols-4 gap-4">
				{products.map((p) => (
					<div key={p.id} className="border rounded-xl p-4 shadow">
						<img
							src={p.imageUrl}
							alt={p.title}
							className="w-full h-32 object-cover mb-2"
						/>
						<h3 className="text-lg font-medium">{p.title}</h3>
						<p className="text-sm">{p.description}</p>
						<p className="font-bold">${p.price}</p>
						<button
							onClick={() => addToCart(p)}
							className="bg-blue-500 text-white px-3 py-1 rounded mt-2 cursor-pointer"
						>
							Add to Cart
						</button>
					</div>
				))}
			</div>

			<h2 className="text-xl font-semibold mt-8">Cart</h2>
			{!cart || !cart.items || cart.items.length === 0 ? (
				<p className="text-gray-500">Cart is empty</p>
			) : (
				<div className="mt-4">
					{cart.items.map((item) => (
						<div
							key={item.id}
							className="border-b py-2 flex justify-between items-center"
						>
							<div>
								<h4 className="font-medium">{item.title}</h4>
								<p className="text-sm text-gray-600">{item.description}</p>
							</div>
							<div className="flex items-center">
								<button
									onClick={() => updateQuantity(item.id, item.quantity - 1)}
									className="px-2"
								>
									-
								</button>
								<span className="px-2">{item.quantity}</span>
								<button
									onClick={() => updateQuantity(item.id, item.quantity + 1)}
									className="px-2"
								>
									+
								</button>
								<button
									onClick={() => removeFromCart(item.id)}
									className="text-red-500 ml-4"
								>
									Remove
								</button>
							</div>
						</div>
					))}
					<div className="mt-4 font-bold flex items-center justify-between">
						<p>Total: ${cart.total.toFixed(2)}</p>
						<button onClick={clearCart} className="cursor-pointer">
							Clear All
						</button>
					</div>
					<button
						onClick={checkout}
						className="bg-green-500 text-white px-4 py-2 rounded mt-4"
					>
						Checkout
					</button>
				</div>
			)}
		</div>
	);
}

export default App;
