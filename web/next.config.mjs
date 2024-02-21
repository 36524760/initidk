/** @type {import('next').NextConfig} */
const nextConfig = {
    async rewrites() {
        return [
            {
                source: '/:path*',
                destination: 'http://localhost:8088/:path*',
            },
        ];
    },
};

export default nextConfig;
