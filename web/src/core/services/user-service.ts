import User from "../interfaces/user.interface";

export const getUsers = async (): Promise<User[]> => {
    try {
        const response = await fetch(`/users`);

        if (!response.ok) {
            throw new Error(`Error fetching users: ${response.status}`);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching users:', error);
        throw error;
    }
};

export const getUserById = async (userId: string): Promise<User> => {
    try {
        const response = await fetch(`/users/${userId}`);

        if (!response.ok) {
            throw new Error(`Error fetching user: ${response.status}`);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error(`Error fetching user with id ${userId}:`, error);
        throw error;
    }
};

export const createUser = async (newUser: User): Promise<User> => {
    try {
        const response = await fetch(`/users`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newUser),
        });

        if (!response.ok) {
            throw new Error(`Error creating user: ${response.status}`);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error creating user:', error);
        throw error;
    }
};

export const updateUser = async (userId: string, updatedUser: User): Promise<User> => {
    try {
        const response = await fetch(`/users/${userId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedUser),
        });

        if (!response.ok) {
            throw new Error(`Error updating user: ${response.status}`);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error(`Error updating user with id ${userId}:`, error);
        throw error;
    }
};

export const deleteUser = async (userId: string): Promise<void> => {
    try {
        const response = await fetch(`/users/${userId}`, {
            method: 'DELETE',
        });

        if (!response.ok) {
            throw new Error(`Error deleting user: ${response.status}`);
        }
    } catch (error) {
        console.error(`Error deleting user with id ${userId}:`, error);
        throw error;
    }
};
